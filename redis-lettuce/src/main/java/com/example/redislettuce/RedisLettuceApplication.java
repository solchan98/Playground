package com.example.redislettuce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RedisLettuceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisLettuceApplication.class, args);
	}
}
