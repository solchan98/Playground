package org.example.springsecurityjwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.login.EmailPasswordAuthenticationConverter;
import org.example.springsecurityjwt.login.EmailPasswordAuthenticationFilter;
import org.example.springsecurityjwt.login.SimplePasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);

        setLoginFilter(httpSecurity);
        setPermitAll(httpSecurity);
        return httpSecurity.build();
    }

    private void setLoginFilter(HttpSecurity httpSecurity) throws Exception {
        EmailPasswordAuthenticationFilter emailPasswordAuthenticationFilter = new EmailPasswordAuthenticationFilter(
                new EmailPasswordAuthenticationConverter(objectMapper));

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new SimplePasswordEncoder());

        emailPasswordAuthenticationFilter.setAuthenticationManager(new ProviderManager(daoAuthenticationProvider));

        httpSecurity
                .addFilterBefore(emailPasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(ahr -> ahr.requestMatchers(emailPasswordAuthenticationFilter.requestMatcher())
                        .permitAll());
    }

    private void setPermitAll(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(ahr -> ahr.requestMatchers(
                        new AntPathRequestMatcher("/test/all", "POST"),
                        new AntPathRequestMatcher("/test/all", "GET"))
                .permitAll()
        );
    }
}
