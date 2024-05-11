package org.example.springjwt.auth;

public interface AuthenticationManager {
    AccessUser verify(Authentication authentication);
}
