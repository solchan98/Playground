package com.springbootswagger.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {

    // 토큰 발급 및 인증 학습이 목적이기 때문에
    // 검증 조건은 일단 생략..
    private String email;
    private String password;
    private String birth;
    private String phone;
}
