package com.example.springbean.생명주기;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanLifeCycle2Config {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanLifeCycle2 beanLifeCycle2() {
        return new BeanLifeCycle2();
    }
}
