package org.example.springsecurityjwt.refresh;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.example.springsecurityjwt.access.AccessTokenProvider;
import org.example.springsecurityjwt.common.AccessUser;
import org.example.springsecurityjwt.common.BearerAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@RequiredArgsConstructor
public class TokenRefreshSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    private final AccessTokenProvider accessTokenProvider;

    record TokenRefreshResponse(String token) {

        public TokenRefreshResponse(BearerAuthenticationToken token) {
            this(token.getName());
        }
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        try {
            TokenRefreshResponse tokenRefreshResponse = new TokenRefreshResponse(
                    accessTokenProvider.createToken((AccessUser) authentication));

            String body =
                    """
                                {
                                    "message": "토큰 재발급에 성공하였습니다.",
                                    "data": %s
                                }
                            """.formatted(objectMapper.writeValueAsString(tokenRefreshResponse)
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
