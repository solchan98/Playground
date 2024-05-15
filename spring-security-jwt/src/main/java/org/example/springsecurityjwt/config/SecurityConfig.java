package org.example.springsecurityjwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.access.AccessTokenProvider;
import org.example.springsecurityjwt.refresh.RefreshTokenProvider;
import org.example.springsecurityjwt.access.AccessTokenAccessDeniedHandler;
import org.example.springsecurityjwt.access.BearerAuthenticationConverter;
import org.example.springsecurityjwt.common.AuthenticationFailureHandlerImpl;
import org.example.springsecurityjwt.common.Role;
import org.example.springsecurityjwt.login.EmailPasswordAuthenticationConverter;
import org.example.springsecurityjwt.login.EmailPasswordAuthenticationFilter;
import org.example.springsecurityjwt.login.EmailPasswordAuthenticationSuccessHandler;
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
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;

    private final UserDetailsService userDetailsService;

    private final AccessTokenProvider accessTokenProvider;

    private final RefreshTokenProvider refreshTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);

        setLoginFilter(httpSecurity);
        setAccessTokenFilter(httpSecurity);
        setPermissions(httpSecurity);

        return httpSecurity.exceptionHandling(eh -> eh.accessDeniedHandler(new AccessTokenAccessDeniedHandler()))
                .build();
    }

    private void setLoginFilter(HttpSecurity httpSecurity) {
        EmailPasswordAuthenticationFilter emailPasswordAuthenticationFilter = new EmailPasswordAuthenticationFilter();
        emailPasswordAuthenticationFilter.setAuthenticationConverter(
                new EmailPasswordAuthenticationConverter(objectMapper));
        emailPasswordAuthenticationFilter.setAuthenticationSuccessHandler(
                new EmailPasswordAuthenticationSuccessHandler(objectMapper, accessTokenProvider, refreshTokenProvider));
        emailPasswordAuthenticationFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandlerImpl());

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new SimplePasswordEncoder());

        emailPasswordAuthenticationFilter.setAuthenticationManager(new ProviderManager(daoAuthenticationProvider));

        httpSecurity.addFilterBefore(emailPasswordAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }


    private void setAccessTokenFilter(HttpSecurity httpSecurity) {
        AuthenticationFilter jwtAuthenticationFilter = new AuthenticationFilter(accessTokenProvider,
                new BearerAuthenticationConverter());

        jwtAuthenticationFilter.setRequestMatcher(new AndRequestMatcher(
                new NegatedRequestMatcher(RequestMatchers.LOGIN),
                new NegatedRequestMatcher(RequestMatchers.OPEN_API)
        ));
        jwtAuthenticationFilter.setFailureHandler(new AuthenticationFailureHandlerImpl());
        jwtAuthenticationFilter.setSuccessHandler((request, response, authentication) -> {
        });

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, EmailPasswordAuthenticationFilter.class);
    }

    private void setPermissions(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(ahr -> ahr.requestMatchers(RequestMatchers.ADMIN)
                .hasAnyAuthority(Role.ADMIN.name())
                .requestMatchers(RequestMatchers.SELLER)
                .hasAnyAuthority(Role.ADMIN.name(), Role.SELLER.name())
                .requestMatchers(RequestMatchers.BUYER)
                .hasAnyAuthority(Role.ADMIN.name(), Role.BUYER.name())
                .requestMatchers(RequestMatchers.PERMIT_ALL)
                .authenticated()
                .requestMatchers(RequestMatchers.OPEN_API)
                .permitAll()
        );
    }
}
