package com.itm.udpserver.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.udp.UnicastSendingMessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class UDPIntegrationClient implements UdpClient {

    private final UnicastSendingMessageHandler udpSendingAdapter;

    @Autowired
    public UDPIntegrationClient(UnicastSendingMessageHandler udpSendingAdapter) {
        this.udpSendingAdapter = udpSendingAdapter;
    }

    @Override
    public void sendMessage(String message) {
        udpSendingAdapter.handleMessage(MessageBuilder.withPayload(message).build());
    }
}
