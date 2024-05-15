package org.example.springsecurityjwt.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class SimpleAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationConverter converter;

    public SimpleAuthenticationProcessingFilter(RequestMatcher requestMatcher, AuthenticationConverter converter) {
        super(requestMatcher);
        this.converter = converter;
    }

    public void setAuthenticationConverter(AuthenticationConverter converter) {
        this.converter = converter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        return this.getAuthenticationManager().authenticate(converter.convert(request));
    }
}
