package com.example.apimodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class ApiModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiModelApplication.class, args);
    }

}
