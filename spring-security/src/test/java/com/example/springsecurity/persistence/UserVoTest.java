package com.example.springsecurity.persistence;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UserDetails를 구현한 UserVO 테스트")
public class UserVoTest {

    private static final int minuteOfLock = 5;

    @Test
    void 잠김_정책에_따라_Lock된_유저는_UserVO의_nonLocked필드가_false로_설정된다() {
        // given
        UserVO userVO = new UserVO(
                "123",
                "sol",
                "1234",
                List.of("user"),
                LocalDateTime.now(),
                Boolean.TRUE
        );

        // when
        boolean isNonLocked = userVO.isAccountNonLocked();

        // then
        assertThat(isNonLocked).isFalse();
    }

    @Test
    void 잠김_정책에_따라_Lock이_풀린_유저는_UserVO의_nonLocked필드가_ture로_설정된다() {
        // given
        UserVO userVO = new UserVO(
                "123",
                "sol",
                "1234",
                List.of("user"),
                LocalDateTime.now().minusMinutes(minuteOfLock + 1),
                Boolean.TRUE
        );

        System.out.println(minuteOfLock);

        // when
        boolean isNonLocked = userVO.isAccountNonLocked();

        // then
        assertThat(isNonLocked).isTrue();
    }
}
