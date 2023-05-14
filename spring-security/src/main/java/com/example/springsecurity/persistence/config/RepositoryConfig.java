package com.example.springsecurity.persistence.config;

import com.example.springsecurity.persistence.UserRepository;
import com.example.springsecurity.persistence.impl.MemoryUserRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepositoryImpl();
    }
}
