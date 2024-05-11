package org.example.springjwt.auth;

import lombok.RequiredArgsConstructor;
import org.example.springjwt.presentation.exception.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailPasswordAuthenticationManager implements AuthenticationManager {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public AccessUser verify(Authentication authentication) {
        EmailPasswordAuthentication emailPasswordAuthentication = (EmailPasswordAuthentication) authentication;
        User user = getUserByEmail(emailPasswordAuthentication.getPrincipal());
        matchPassword(emailPasswordAuthentication.getCredentials(), user.getEncryptedPassword());

        return AccessUser.from(user);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("아이디 혹은 비밀번호를 확인하세요."));
    }

    private void matchPassword(String plainPassword, String encodedPassword) {
        boolean match = passwordEncoder.match(plainPassword, encodedPassword);
        if (!match) {
            throw new AuthenticationException("아이디 혹은 비밀번호를 확인하세요.");
        }
    }
}
