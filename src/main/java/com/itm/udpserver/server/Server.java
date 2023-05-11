package com.itm.udpserver.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itm.udpserver.entity.BasePacket;
import com.itm.udpserver.service.PacketService;
import com.itm.udpserver.service.SystemConfig;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;

@Component
@RequiredArgsConstructor
public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    @Value("${udp.port}")
    private Integer port;
    @Value("${buffer.length}")
    private Integer bufferLength;
    @Value("${hostname}")
    private String hostname;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PacketService packetService;


    public void start() {
        logger.info("Server started");
        byte[] buffer = new byte[bufferLength];
        SocketAddress address = new InetSocketAddress(hostname, port);
        DatagramSocket socket;
        try {
            socket = new DatagramSocket(address);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);

        while (!SystemConfig.isNeedStop()) {

            try {
                socket.receive(request);
                byte[] data = request.getData();
                BasePacket packet = packetService.enrichEntity(data);
                packetService.update(packet);
                String jsonPacket = objectMapper.writeValueAsString(packet);
                logger.info(jsonPacket);
            } catch (IOException e) {
                socket.close();
                throw new RuntimeException(e);
            }
        }
    }

}
