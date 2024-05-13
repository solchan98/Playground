package org.example.springsecurityjwt.presentation.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleException(AuthenticationException e) {
        return ResponseEntity
                .status(401)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleException(AccessDeniedException e) {
        return ResponseEntity
                .status(403)
                .body(new ErrorResponse(e.getMessage()));
    }
}
