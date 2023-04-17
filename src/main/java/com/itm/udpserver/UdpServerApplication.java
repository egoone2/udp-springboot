package com.itm.udpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class UdpServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdpServerApplication.class, args);
	}

}
