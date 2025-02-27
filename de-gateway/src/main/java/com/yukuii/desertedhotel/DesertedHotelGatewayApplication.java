package com.yukuii.desertedhotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DesertedHotelGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesertedHotelGatewayApplication.class, args);
    }
}
