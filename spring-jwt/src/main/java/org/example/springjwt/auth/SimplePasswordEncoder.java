package org.example.springjwt.auth;

import org.springframework.stereotype.Component;

@Component
public class SimplePasswordEncoder implements PasswordEncoder {

    private static final String SALT = "******";

    @Override
    public String encode(String plainPassword) {
        return plainPassword.concat(SALT);
    }

    @Override
    public String decode(String decode) {
        return decode.replace(SALT, "");
    }

    @Override
    public boolean match(String plainPassword, String encodedPassword) {
        return encode(plainPassword).equals(encodedPassword);
    }
}
