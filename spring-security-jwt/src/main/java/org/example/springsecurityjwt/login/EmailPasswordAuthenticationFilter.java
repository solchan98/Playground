package org.example.springsecurityjwt.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.springsecurityjwt.config.RequestMatchers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class EmailPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationConverter converter;

    public EmailPasswordAuthenticationFilter() {
        super(RequestMatchers.LOGIN);
    }

    public EmailPasswordAuthenticationFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
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
