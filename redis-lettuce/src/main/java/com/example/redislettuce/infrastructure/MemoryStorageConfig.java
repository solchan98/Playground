package com.example.redislettuce.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryStorageConfig {

    @Bean
    public BookInMemoryStorage bookInMemoryStorage() {
        return new BookInMemoryStorage();
    }
}
