package com.springbootjwt.account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignUpRequest {

    private final String email;

    private final String password;

    private final String nickname;
}
