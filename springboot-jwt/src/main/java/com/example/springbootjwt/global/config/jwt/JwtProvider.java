package com.example.springbootjwt.global.config.jwt;

import com.example.springbootjwt.domain.user.service.CustomAccountDetailsService;
import com.example.springbootjwt.global.common.exception.BadRequestException;
import com.example.springbootjwt.global.common.redis.RedisService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    private final CustomAccountDetailsService customAccountDetailsService;
    private final RedisService redisService;
    @Value("${spring.jwt.secret-key}")
    private String secretKey;

    @Value("${spring.jwt.blacklist.access-token}")
    private String blackListATPrefix;

    // 의존성 주입 후, 초기화를 수행
    // 객체 초기화, secretKey Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public void logout(String userId, String accessToken) {
        long expiredAccessTokenTime = getExpiredTime(accessToken).getTime() - new Date().getTime();
        redisService.setValues(blackListATPrefix + accessToken, userId, Duration.ofMillis(expiredAccessTokenTime));
        redisService.deleteValues(userId); // Delete RefreshToken In Redis
    }

    public void checkRefreshToken(String userId, String refreshToken) {
        String redisRT = redisService.getValues(userId);
        if (!refreshToken.equals(redisRT)) {
            throw new BadRequestException("토큰이 만료되었습니다.");
        }
    }

    public String createAccessToken(String userId, String roles) {
        Long tokenInvalidTime = 1000L * 60 * 3; // 3m
        return this.createToken(userId, roles, tokenInvalidTime);
    }

    public String createRefreshToken(String userId, String roles) {
        Long tokenInvalidTime = 1000L * 60 * 60 * 24; // 1d
        String refreshToken = this.createToken(userId, roles, tokenInvalidTime);
        redisService.setValues(userId, refreshToken, Duration.ofMillis(tokenInvalidTime));
        return refreshToken;
    }

    private String createToken(String userId, String roles, Long tokenInvalidTime){
        Claims claims = Jwts.claims().setSubject(userId); // claims 생성 및 payload 설정
        claims.put("roles", roles); // 권한 설정, key/ value 쌍으로 저장
        Date date = new Date();
        return Jwts.builder()
                .setClaims(claims) // 발행 유저 정보 저장
                .setIssuedAt(date) // 발행 시간 저장
                .setExpiration(new Date(date.getTime() + tokenInvalidTime)) // 토큰 유효 시간 저장
                .signWith(SignatureAlgorithm.HS256, secretKey) // 해싱 알고리즘 및 키 설정
                .compact(); // 생성
    }

    public Authentication validateToken(HttpServletRequest request, String token) {
        String exception = "exception";
        try {
            String expiredAT = redisService.getValues(blackListATPrefix + token);
           if (expiredAT != null) {
                throw new ExpiredJwtException(null, null, null);
            }
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return getAuthentication(token);
        } catch (MalformedJwtException  | SignatureException | UnsupportedJwtException e) {
            request.setAttribute(exception, "토큰의 형식을 확인하세요");
        } catch (ExpiredJwtException e) {
            request.setAttribute(exception, "토큰이 만료되었습니다.");
        } catch (IllegalArgumentException e) {
            request.setAttribute(exception, "JWT compact of handler are invalid");
        }
        return null;
    }

    private Authentication getAuthentication(String token) {
        UserDetails userDetails = customAccountDetailsService.loadUserByUsername(getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserEmail(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    private Date getExpiredTime(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getExpiration();
    }
}
