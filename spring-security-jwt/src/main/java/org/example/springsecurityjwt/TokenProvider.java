package org.example.springsecurityjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

public abstract class TokenProvider implements AuthenticationManager {

    private SecretKey KEY = Keys.hmacShaKeyFor(
            Decoders.BASE64.decode("secretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKey"));

    public String createToken(String subject, Map<String, Object> payload, long ttl) {
        Claims claims = Jwts.claims()
                .subject(subject)
                .add(payload)
                .build();
        Date date = new Date();

        return Jwts.builder()
                .claims(claims)
                .issuedAt(date)
                .expiration(new Date(date.getTime() + ttl))
                .signWith(KEY)
                .compact();
    }

    public Claims verify(String token) {
        return Jwts.parser()
                .verifyWith(KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public abstract void checkSupported(Authentication authentication);
}
