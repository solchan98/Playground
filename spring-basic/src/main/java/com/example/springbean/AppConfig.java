package com.example.springbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public StoreService storeService() {
        return new StoreServiceImpl();
    }
}
