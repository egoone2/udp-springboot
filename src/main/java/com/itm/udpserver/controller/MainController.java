package com.itm.udpserver.controller;

import com.itm.udpserver.server.Server;
import com.itm.udpserver.service.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final Server server;

    @Autowired
    public MainController(Server server) {
        this.server = server;
    }

    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                server.start();
            }
        }).start();
    }
    
    @GetMapping("/exit")
    public void stopPage(){
        SystemConfig.setNeedStop(true);
        SystemConfig.pauseSecWithPeriod5(5L);
        logger.info("Exit command...");
        System.exit(0);
    }
}
