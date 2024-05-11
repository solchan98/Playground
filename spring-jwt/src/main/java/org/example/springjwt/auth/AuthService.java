package org.example.springjwt.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    public AuthService(
            @Qualifier(value = "emailPasswordAuthenticationManager") AuthenticationManager authenticationManager,
            TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public String signIn(EmailPasswordAuthentication emailPasswordAuthentication) {
        return tokenProvider.createAccessToken(authenticationManager.verify(emailPasswordAuthentication));
    }
}
