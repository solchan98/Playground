package org.example.springsecurityjwt.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.example.springsecurityjwt.common.AccessUser;
import org.example.springsecurityjwt.common.TokenProvider;
import org.example.springsecurityjwt.db.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class EmailPasswordAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;

    public EmailPasswordAuthenticationSuccessHandler(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        AccessUser authenticated = AccessUser.authenticated((UserEntity) authentication.getPrincipal());

        String body =
                """
                            {
                                "message": "로그인에 성공하였습니다.",
                                "data": "%s"
                            }
                        """.formatted(tokenProvider.createAccessToken(authenticated));

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try {
            response.getWriter().write(body);
        } catch (IOException e) {
            throw new RuntimeException("서버에서 문제가 발생하였습니다.", e);
        }
    }
}
