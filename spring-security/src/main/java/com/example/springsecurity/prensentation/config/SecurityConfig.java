package com.example.springsecurity.prensentation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity,
            UserDetailsService userDetailsService
    ) throws Exception {
        httpSecurity
                .userDetailsService(userDetailsService)
                .formLogin()
                .successHandler(new FormLoginSuccessHandler())
                .permitAll();

        configCustomerApi(httpSecurity);
        configSellerApi(httpSecurity);
        configAdminApi(httpSecurity);

        httpSecurity
                .authorizeHttpRequests()
                .anyRequest()
                .hasAnyAuthority("admin");

        return httpSecurity.build();
    }

    private void configCustomerApi(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/customer/**")
                .hasAnyAuthority("admin", "customer", "seller");

    }

    private void configSellerApi(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/seller/**")
                .hasAnyAuthority("admin", "seller");
    }

    private void configAdminApi(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/admin/**")
                .hasAnyAuthority("admin");
    }
}
