package org.example.springjwt.auth;

public interface PasswordEncoder {

    String encode(String plainPassword);

    String decode(String encodedPassword);

    boolean match(String plainPassword, String encodedPassword);
}
