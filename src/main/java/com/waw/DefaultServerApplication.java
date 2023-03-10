package com.waw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DefaultServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefaultServerApplication.class, args);
    }
}
