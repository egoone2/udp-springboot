package com.itm.udpserver.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itm.udpserver.entity.BasePacket;
import com.itm.udpserver.service.PacketService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Component
@Slf4j
@RequiredArgsConstructor
public class Server {

    @Value("${udp.port}")
    private Integer port;
    @Value("${buffer.length}")
    private Integer bufferLength;
    @Value("${hostname}")
    private String hostname;

    private DatagramSocket socket;
    private DatagramPacket request;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PacketService packetService;


    @SneakyThrows
    public void start() {
        log.info("Server started");
        byte[] buffer = new byte[bufferLength];
        SocketAddress address = new InetSocketAddress(hostname, port);
        socket = new DatagramSocket(address);
        request = new DatagramPacket(buffer, buffer.length);

        while (true) {

            socket.receive(request);
            byte[] data = request.getData();
            BasePacket packet = packetService.enrichEntity(data);
            packetService.update(packet);
            String jsonPacket = objectMapper.writeValueAsString(packet);
            log.info(jsonPacket);
        }
    }

    @PostConstruct
    private void startServer() {
        this.start();
    }

}
