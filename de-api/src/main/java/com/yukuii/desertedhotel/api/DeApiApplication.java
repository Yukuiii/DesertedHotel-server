package com.yukuii.desertedhotel.api;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootConfiguration
@EnableFeignClients(basePackages = "com.yukuii.desertedhotel.api")
public class DeApiApplication {
   
} 