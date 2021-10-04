package com.example.springbootbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBatchApplication.class, args);
    }

}
