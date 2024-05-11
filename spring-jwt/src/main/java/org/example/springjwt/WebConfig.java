package org.example.springjwt;

import org.example.springjwt.auth.AuthInterceptor;
import org.example.springjwt.auth.AuthenticationManager;
import org.example.springjwt.auth.AuthorizationManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationManager authenticationManager;

    private final AuthorizationManager authorizationManager;

    public WebConfig(@Qualifier("jwtAuthenticationManager") AuthenticationManager authenticationManager,
            AuthorizationManager authorizationManager) {
        this.authenticationManager = authenticationManager;
        this.authorizationManager = authorizationManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(authenticationManager, authorizationManager));
    }

}
