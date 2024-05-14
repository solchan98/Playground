package org.example.springsecurityjwt.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.StringUtil;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StreamUtils;

@RequiredArgsConstructor
public class EmailPasswordAuthenticationConverter implements AuthenticationConverter {

    private final ObjectMapper objectMapper;

    record LoginRequest(String email, String password) {
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        LoginRequest loginRequest = parseToLoginRequest(request);

        return new UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password);
    }

    private LoginRequest parseToLoginRequest(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            String stringOfBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            LoginRequest loginRequest = objectMapper.readValue(stringOfBody, LoginRequest.class);
            checkRequestData(loginRequest);
            return loginRequest;
        } catch (JsonProcessingException jpe) {
            throw new InsufficientAuthenticationException("로그인 요청 데이터를 확인하세요.");
        } catch (IOException e) {
            throw new RuntimeException("서버에서 문제가 발생하였습니다.", e);
        }
    }

    private void checkRequestData(LoginRequest loginRequest) {
        if (StringUtil.isNullOrEmpty(loginRequest.email) || StringUtil.isNullOrEmpty(loginRequest.password)) {
            throw new InsufficientAuthenticationException("로그인 요청 데이터를 확인하세요.");
        }
    }
}
