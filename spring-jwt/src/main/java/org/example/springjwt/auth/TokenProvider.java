package org.example.springjwt.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.example.springjwt.presentation.exception.AuthenticationException;
import org.example.springjwt.presentation.exception.AuthorizationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final ObjectMapper objectMapper;

    private static final SecretKey KEY = Keys.hmacShaKeyFor(
            Decoders.BASE64.decode("secretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKey"));

    public String createAccessToken(AccessUser accessUser) {
        try {
            long tokenLive = 1000L * 60L * 60L; // 1h
            String userInfo = objectMapper.writeValueAsString(accessUser);
            Claims claims = Jwts.claims()
                    .subject("access token")
                    .add("userInfo", userInfo)
                    .build();
            Date date = new Date();
            return Jwts.builder()
                    .claims(claims)
                    .issuedAt(date)
                    .expiration(new Date(date.getTime() + tokenLive))
                    .signWith(KEY)
                    .compact();
        } catch (JsonProcessingException e) {
            // TODO 서버에러 예외 처리
            throw new RuntimeException(e);
        }
    }

    public AccessUser verify(String token) {
        try {
            Claims payload = Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return objectMapper.readValue(payload.get("userInfo", String.class), AccessUser.class);
        } catch (MalformedJwtException | JsonProcessingException mje) {
            throw new AuthenticationException("인증 정보를 확인하세요.", mje);
        } catch (JwtException je) {
            throw new AuthorizationException(je.getMessage(), je);
        }
    }
}
