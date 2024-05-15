package org.example.springsecurityjwt.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.common.AccessUser;
import org.example.springsecurityjwt.common.BearerAuthenticationToken;
import org.example.springsecurityjwt.common.TokenProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component(value = "accessTokenProvider")
@RequiredArgsConstructor
public class AccessTokenProvider extends TokenProvider {

    private static final String TOKEN_SUBJECT = "access token";

    private final ObjectMapper objectMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Claims payload = verify(authentication.getName());
            checkSupported(payload);

            AccessUser accessUser = objectMapper.readValue(payload.get("userInfo", String.class), AccessUser.class);
            accessUser.setAuthenticated(true);

            return accessUser;
        } catch (MalformedJwtException | JsonProcessingException mje) {
            throw new BadCredentialsException("인증 정보를 확인하세요.", mje);
        } catch (JwtException je) {
            throw new BadCredentialsException(je.getMessage(), je);
        }
    }

    public BearerAuthenticationToken createToken(AccessUser accessUser) {
        try {
            long tokenLive = 1000L * 60L * 60L; // 1h
            String userInfo = objectMapper.writeValueAsString(accessUser);

            return super.createToken(TOKEN_SUBJECT, Map.of("userInfo", userInfo), tokenLive);
        } catch (JsonProcessingException e) {
            // TODO 서버에러 예외 처리
            throw new RuntimeException(e);
        }
    }

    @Override
    public void checkSupported(Claims claims) {
        if (!TOKEN_SUBJECT.equals(claims.getSubject())) {
            throw new BadCredentialsException("인증 정보를 확인하세요.");
        }
    }
}
