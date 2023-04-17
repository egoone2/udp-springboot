package com.itm.udpserver.controllers;

import com.itm.udpserver.client.UDPIntegrationClient;
import com.itm.udpserver.client.UdpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final UdpClient udpClient;

    @Autowired
    public MessageController(UDPIntegrationClient udpClient) {
        this.udpClient = udpClient;
    }

    @PostMapping
    public void sendMessage(@RequestBody String message) {
        System.out.println("Message: " + message);
        udpClient.sendMessage(message);
    }
}
