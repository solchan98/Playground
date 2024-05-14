package org.example.springsecurityjwt.access;

import io.netty.util.internal.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StringUtils;

public class BearerAuthenticationConverter implements AuthenticationConverter {

    private static final String AUTHENTICATION_SCHEME = "BEARER";

    @Override
    public Authentication convert(HttpServletRequest request) {
        String header = request.getHeader("Authorization").replace(" ", "");
        if (StringUtil.isNullOrEmpty(header) || !StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME)) {
            throw new BadCredentialsException("토큰 이상");
        }

        if (header.equalsIgnoreCase(AUTHENTICATION_SCHEME)) {
            throw new BadCredentialsException("토큰 이상");
        }

        return new JwtAuthentication(header.substring(AUTHENTICATION_SCHEME.length()));
    }
}
