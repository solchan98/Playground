package org.example.springjwt.auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.springjwt.auth.PasswordEncoder;
import org.example.springjwt.auth.SimplePasswordEncoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimplePasswordEncoderTest {

    PasswordEncoder passwordEncoder = new SimplePasswordEncoder();

    @Test
    @DisplayName("평문 비밀번호를 인코딩할 수 있다.")
    void encode() {
        String plainPassword = "a1234567!";
        assertThat(passwordEncoder.encode(plainPassword)).isNotEqualTo(plainPassword);
    }

    @Test
    @DisplayName("인코딩 패스워드롤 디코딩할 수 있다.")
    void decode() {
        String plainPassword = "a1234567!";
        String encodedPassword = passwordEncoder.encode(plainPassword);

        assertThat(passwordEncoder.decode(encodedPassword)).isEqualTo(plainPassword);
    }

    @Test
    @DisplayName("평문 비밀번호화 인코딩된 비밀번호를 비교할 수 있다.")
    void match() {
        String plainPassword = "a1234567!";
        String encodedPassword = passwordEncoder.encode(plainPassword);

        assertThat(passwordEncoder.match(plainPassword, encodedPassword)).isTrue();
        assertThat(passwordEncoder.match(plainPassword, "123")).isFalse();
    }
}
