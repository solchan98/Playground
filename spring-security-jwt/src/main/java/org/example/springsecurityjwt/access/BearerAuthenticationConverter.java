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
        String header = request.getHeader("Authorization");
        if (StringUtil.isNullOrEmpty(header)) {
            throw new BadCredentialsException("인증 정보를 확인하세요.");
        }

        header = header.replace(" ", "");
        if (!StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME)) {
            throw new BadCredentialsException("인증 정보를 확인하세요.");
        }

        return new AccessAuthenticationToken(header.substring(AUTHENTICATION_SCHEME.length()));
    }
}
