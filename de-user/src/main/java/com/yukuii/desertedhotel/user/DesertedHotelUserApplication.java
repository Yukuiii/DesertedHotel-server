package com.yukuii.desertedhotel.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DesertedHotelUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesertedHotelUserApplication.class, args);
    }
} 