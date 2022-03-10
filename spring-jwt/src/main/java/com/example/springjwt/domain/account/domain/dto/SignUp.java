package com.example.springjwt.domain.account.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUp {
    private String email;
    private String password;
}
