package org.example.springjwt.auth;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import org.example.springjwt.auth.AuthenticationManager;
import org.example.springjwt.auth.EmailPasswordAuthentication;
import org.example.springjwt.auth.EmailPasswordAuthenticationManager;
import org.example.springjwt.auth.PasswordEncoder;
import org.example.springjwt.auth.Roles;
import org.example.springjwt.auth.User;
import org.example.springjwt.auth.UserRepository;
import org.example.springjwt.presentation.exception.AuthenticationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmailPasswordAuthenticationManagerTest {

    PasswordEncoder passwordEncoder = mock();
    UserRepository userRepository = mock();

    AuthenticationManager authenticationManager = new EmailPasswordAuthenticationManager(userRepository,
            passwordEncoder);

    @Test
    @DisplayName("로그인 검증 성공 시, 아무 예외가 발생하지 않는다.")
    void verify() {
        // given
        String email = "sol@sol.com";
        String plainPassword = "a1234567";
        EmailPasswordAuthentication emailPasswordAuthentication = new EmailPasswordAuthentication(email, plainPassword);
        given(userRepository.findByEmail(email)).willReturn(
                Optional.of(new User(1L, email, "sol", plainPassword, new Roles())));
        given(passwordEncoder.match(anyString(), anyString())).willReturn(true);

        // when then
        assertDoesNotThrow(() -> authenticationManager.verify(emailPasswordAuthentication));
    }

    @Test
    @DisplayName("유저가 존재하지 않는 경우, 401 예외")
    void verify_fail_when_not_exists_user() {
        String email = "sol@sol.com";
        String plainPassword = "a1234567";
        EmailPasswordAuthentication emailPasswordAuthentication = new EmailPasswordAuthentication(email, plainPassword);
        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        // when
        Throwable throwable = catchThrowable(() -> authenticationManager.verify(emailPasswordAuthentication));

        // then
        assertThat(throwable).isInstanceOf(AuthenticationException.class)
                .hasMessage("아이디 혹은 비밀번호를 확인하세요.");
    }

    @Test
    @DisplayName("비밀번호가 다른 경우, 401 예외")
    void verify_fail_when_not_matched_password() {
        String email = "sol@sol.com";
        String plainPassword = "a1234567";
        EmailPasswordAuthentication emailPasswordAuthentication = new EmailPasswordAuthentication(email, plainPassword);
        given(userRepository.findByEmail(email)).willReturn(
                Optional.of(new User(1L, email, "sol", plainPassword, new Roles())));
        given(passwordEncoder.match(anyString(), anyString())).willReturn(false);

        // when
        Throwable throwable = catchThrowable(() -> authenticationManager.verify(emailPasswordAuthentication));

        // then
        assertThat(throwable).isInstanceOf(AuthenticationException.class)
                .hasMessage("아이디 혹은 비밀번호를 확인하세요.");
    }
}
