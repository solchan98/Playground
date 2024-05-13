package org.example.springsecurityjwt.login;

import org.springframework.security.crypto.password.PasswordEncoder;

public class SimplePasswordEncoder implements PasswordEncoder {

    private static final String SALT = "******";

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString().concat(SALT);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword.toString()).equals(encodedPassword);
    }
}
