package com.example.springjwt.domain.account.domain.dto.response;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final String email;
    private final String accessToken;
    private final String refreshToken;

    private LoginResponse(String email, String accessToken, String refreshToken) {
        this.email = email;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static LoginResponse of(String email, String accessToken, String refreshToken) {
        return new LoginResponse(email, accessToken, refreshToken);
    }
}
