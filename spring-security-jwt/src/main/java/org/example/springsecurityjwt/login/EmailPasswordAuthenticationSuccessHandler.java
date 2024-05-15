package org.example.springsecurityjwt.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.AccessTokenProvider;
import org.example.springsecurityjwt.RefreshTokenProvider;
import org.example.springsecurityjwt.common.AccessUser;
import org.example.springsecurityjwt.db.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
public class EmailPasswordAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    private final AccessTokenProvider accessTokenProvider;

    private final RefreshTokenProvider refreshTokenProvider;

    record LoginResponse(String accessToken, String refreshToken) {
        public LoginResponse(Authentication accessToken, Authentication refreshToken) {
            this(accessToken.getName(), refreshToken.getName());
        }
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        try {
            AccessUser authenticated = AccessUser.authenticated((UserEntity) authentication.getPrincipal());

            Authentication accessToken = accessTokenProvider.createToken(authenticated);
            Authentication refreshToken = refreshTokenProvider.createToken(authenticated.getEmail());

            String body =
                    """
                                {
                                    "message": "로그인에 성공하였습니다.",
                                    "data": %s
                                }
                            """.formatted(objectMapper.writeValueAsString(new LoginResponse(accessToken, refreshToken))
                    );

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.OK.value());
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write(body);
        } catch (IOException e) {
            throw new RuntimeException("서버에서 문제가 발생하였습니다.", e);
        }
    }
}
