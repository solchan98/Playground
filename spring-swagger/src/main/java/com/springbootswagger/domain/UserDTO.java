package com.springbootswagger.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {

    // 토큰 발급 및 인증 학습이 목적이기 때문에
    // 검증 조건은 일단 생략..
    @ApiModelProperty(value = "이메일", example = "solchan@hello.swagger",required = true)
    private String email;
    @ApiModelProperty(value = "비밀번호", example = "swagger123", required = true)
    private String password;
    @ApiModelProperty(value = "생년월일", example = "980427", required = true)
    private String birth;
    @ApiModelProperty(value = "휴대폰 번호", example = "010-3233-1111", required = false)
    private String phone;
}
