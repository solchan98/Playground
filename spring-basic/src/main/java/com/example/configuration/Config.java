package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Called memberRepository");
        return new MemberRepository();
    }

    @Bean
    public MemberService memberService() {
        System.out.println("Called memberService");
        return new MemberService(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Called orderService");
        return new OrderService(memberRepository());
    }
}
