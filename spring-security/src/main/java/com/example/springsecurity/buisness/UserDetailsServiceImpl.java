package com.example.springsecurity.buisness;

import com.example.springsecurity.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
   private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String messageOfException = "유저를 찾을 수 없습니다.";
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(messageOfException));
    }
}
