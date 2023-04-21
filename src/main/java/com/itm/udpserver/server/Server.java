package com.itm.udpserver.server;

import com.itm.udpserver.entity.BasePacket;
import com.itm.udpserver.repository.EquipRepository;
import com.itm.udpserver.service.PacketService;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Component
public class Server extends Thread {
    @Value("${udp.port}")
    private Integer port;
    @Value("${buffer.length}")
    private Integer bufferLength;

    private final EquipRepository repository;
    private DatagramSocket socket;
    private DatagramPacket request;

    @Autowired
    public Server(EquipRepository repository) {
        this.repository = repository;
    }


    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Server started");

        byte[] buffer = new byte[bufferLength];
        socket = new DatagramSocket(port);
        request = new DatagramPacket(buffer, buffer.length);

        while (true) {

            socket.receive(request);
            byte[] data = request.getData();
            BasePacket packet = PacketService.enrichEntity(data);
            repository.update(packet);

            System.out.println(packet);
        }
    }

    @PostConstruct
    private void startServer() {
        this.start();
    }

}
