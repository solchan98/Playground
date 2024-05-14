package org.example.springsecurityjwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.access.AccessTokenAccessDeniedHandler;
import org.example.springsecurityjwt.access.BearerAuthenticationConverter;
import org.example.springsecurityjwt.common.AuthenticationFailureHandlerImpl;
import org.example.springsecurityjwt.common.TokenProvider;
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
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

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
        setAccessTokenFilter(httpSecurity);

        OrRequestMatcher sellerRequestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher("/seller", "GET"),
                new AntPathRequestMatcher("/seller", "POST")
        );

        OrRequestMatcher buyerRequestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher("/buyer", "GET"),
                new AntPathRequestMatcher("/buyer", "POST")
        );

        OrRequestMatcher adminRequestMatcher = new OrRequestMatcher(new AntPathRequestMatcher("/admin", "GET"),
                new AntPathRequestMatcher("/admin", "POST")
        );

        return httpSecurity
                .exceptionHandling(eh -> eh.accessDeniedHandler(new AccessTokenAccessDeniedHandler()))
                .authorizeHttpRequests(ahr -> ahr.requestMatchers(adminRequestMatcher)
                        .hasAnyAuthority("ADMIN")
                        .requestMatchers(sellerRequestMatcher)
                        .hasAnyAuthority("SELLER", "ADMIN")
                        .requestMatchers(buyerRequestMatcher)
                        .hasAnyAuthority("BUYER", "ADMIN")
                        .requestMatchers("/test/all")
                        .permitAll()
                )
                .build();
    }

    private void setLoginFilter(HttpSecurity httpSecurity) {
        EmailPasswordAuthenticationFilter emailPasswordAuthenticationFilter = new EmailPasswordAuthenticationFilter(
                new EmailPasswordAuthenticationConverter(objectMapper),
                new TokenProvider(objectMapper));

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new SimplePasswordEncoder());

        emailPasswordAuthenticationFilter.setAuthenticationManager(new ProviderManager(daoAuthenticationProvider));

        httpSecurity.addFilterBefore(emailPasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


    private void setAccessTokenFilter(HttpSecurity httpSecurity) {
        TokenProvider tokenProvider = new TokenProvider(objectMapper);
        AuthenticationFilter jwtAuthenticationFilter = new AuthenticationFilter(tokenProvider,
                new BearerAuthenticationConverter());

        jwtAuthenticationFilter.setRequestMatcher(
                new AndRequestMatcher(
                        new NegatedRequestMatcher(EmailPasswordAuthenticationFilter.requestMatcher()),
                        new NegatedRequestMatcher(new AntPathRequestMatcher("/test/all", "POST")),
                        new NegatedRequestMatcher(new AntPathRequestMatcher("/test/all", "GET"))));
        jwtAuthenticationFilter.setFailureHandler(new AuthenticationFailureHandlerImpl());
        jwtAuthenticationFilter.setSuccessHandler((request, response, authentication) -> {
        });

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, EmailPasswordAuthenticationFilter.class);
    }
}
