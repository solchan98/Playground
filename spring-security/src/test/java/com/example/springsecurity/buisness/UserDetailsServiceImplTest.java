package com.example.springsecurity.buisness;

import com.example.springsecurity.persistence.UserRepository;
import com.example.springsecurity.persistence.UserVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@DisplayName("UserDetailsService를 구현한 UserDetailsServiceImpl 테스트")
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void user가_존재하지_않다면_username_not_found_exception이_발생한다() {
        // given
        String invalidUsername = "9d7894f9-3e92-415c-a3db-cdab95d31aa0";
        String messageOfException = "유저를 찾을 수 없습니다.";
        given(userRepository.findByUsername(invalidUsername)).willReturn(Optional.empty());
        
        // when then
        Exception exception = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(invalidUsername)
        );
        assertThat(exception.getMessage()).isEqualTo(messageOfException);
    }

    @Test
    /*
      return value is UserVO,
      return value type is UserDetails
      */
    void user가_존재한다면_userDetails를_정상적으로_응답한다() {
        // given
        String username = "4d431bee-bc80-4452-9fb2-328cd9a47ee7";
        String password = "1234";
        List<String> roles = List.of("user");
        UserVO userVO =
                new UserVO(username, "sol", password, roles, LocalDateTime.MIN, Boolean.TRUE);
        given(userRepository.findByUsername(username)).willReturn(Optional.of(userVO));

        // when
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        List<String> authoritiesOfUserDetails = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        // then
        assertAll(
                () -> assertThat(userDetails.getUsername()).isEqualTo(username),
                () -> assertThat(userDetails.getPassword()).isEqualTo(password),
                () -> assertThat(authoritiesOfUserDetails.size()).isEqualTo(roles.size())
        );
    }
}
