package com.itm.udpserver.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UDPInboundMessageHandler {

    @ServiceActivator(inputChannel = "inboundChannel")
    public void handeMessage(Message message, @Headers Map<String, Object> headerMap) {
        System.out.printf("Received UDP message: %s", new String((byte[]) message.getPayload()));
    }

}
