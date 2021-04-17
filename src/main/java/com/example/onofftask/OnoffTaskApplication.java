package com.example.onofftask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OnoffTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnoffTaskApplication.class, args);
    }

}
