package com.example.springbootjwt.global.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ExceptionDto {
    private String message;
    private HttpStatus status;
}
