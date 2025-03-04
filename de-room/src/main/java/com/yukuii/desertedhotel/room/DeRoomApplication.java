package com.yukuii.desertedhotel.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeRoomApplication.class, args);
    }

}
