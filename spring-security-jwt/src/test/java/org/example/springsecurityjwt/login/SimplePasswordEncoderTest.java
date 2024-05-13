package org.example.springsecurityjwt.login;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.springsecurityjwt.login.SimplePasswordEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class SimplePasswordEncoderTest {

    PasswordEncoder passwordEncoder = new SimplePasswordEncoder();

    @Test
    @DisplayName("평문 비밀번호를 인코딩할 수 있다.")
    void encode() {
        String plainPassword = "a1234567!";
        assertThat(passwordEncoder.encode(plainPassword)).isNotEqualTo(plainPassword);
    }

    @Test
    @DisplayName("평문 비밀번호화 인코딩된 비밀번호를 비교할 수 있다.")
    void match() {
        String plainPassword = "a1234567!";
        String encodedPassword = passwordEncoder.encode(plainPassword);

        assertThat(passwordEncoder.matches(plainPassword, encodedPassword)).isTrue();
        assertThat(passwordEncoder.matches(plainPassword, "123")).isFalse();
    }
}
