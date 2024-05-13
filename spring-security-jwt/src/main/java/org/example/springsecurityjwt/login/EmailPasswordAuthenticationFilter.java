package org.example.springsecurityjwt.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class EmailPasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationConverter converter;

    private static final RequestMatcher requestMatcher = new AntPathRequestMatcher("/auth/sign-in", "POST");

    public EmailPasswordAuthenticationFilter(AuthenticationConverter converter) {
        super(requestMatcher);
        this.setAuthenticationSuccessHandler(new EmailPasswordAuthenticationSuccessHandler());
        this.setAuthenticationFailureHandler(new EmailPasswordAuthenticationFailureHandler());
        this.converter = converter;
    }

    public RequestMatcher requestMatcher() {
        return requestMatcher;
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
