package org.example.springjwt.auth;

import org.example.springjwt.auth.PasswordEncoder;

public class NoneEncodePasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(String plainPassword) {
        return plainPassword;
    }

    @Override
    public String decode(String encodedPassword) {
        return encodedPassword;
    }

    @Override
    public boolean match(String plainPassword, String encodedPassword) {
        return plainPassword.equals(encodedPassword);
    }
}
