package com.yukuii.desertedhotel.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.yukuii.desertedhotel.api")
@SpringBootApplication
public class DesertedHotelAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(DesertedHotelAuthApplication.class, args);
    }
}
