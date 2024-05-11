package org.example.springjwt.auth;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.example.springjwt.presentation.exception.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements AuthenticationManager {

    private final TokenProvider tokenProvider;

    @Override
    public AccessUser verify(Authentication authentication) {
        String token = (String) authentication.getCredentials();
        if (StringUtil.isNullOrEmpty(token)) {
            throw new AuthenticationException("인증 정보를 확인하세요.");
        }
        return tokenProvider.verify(token);
    }
}