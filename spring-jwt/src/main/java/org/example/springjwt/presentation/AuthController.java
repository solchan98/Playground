package org.example.springjwt.presentation;

import lombok.RequiredArgsConstructor;
import org.example.springjwt.auth.AuthService;
import org.example.springjwt.auth.EmailPasswordAuthentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> signIn(@RequestBody EmailPasswordAuthentication emailPasswordAuthentication) {
        return ResponseEntity.ok()
                .body(new LoginResponse(authService.signIn(emailPasswordAuthentication)));
    }

    @PostMapping("/sign-up")
    public Object signUp() {
        return null;
    }
}
