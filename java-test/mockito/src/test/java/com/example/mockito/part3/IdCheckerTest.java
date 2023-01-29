package com.example.mockito.part3;

import com.example.mockito.part3.domain.IdCard;
import com.example.mockito.part3.domain.IdChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("IdChecker Test")
public class IdCheckerTest {

    @Test
    @DisplayName("check - 담배를 구매할 수 없는 나이(19살)인 경우")
    void whenAgeIs19() {
        // given
        IdChecker idChecker = new IdChecker();
        IdCard idCard = new IdCard("sol", 19);

        // when
        boolean valid = idChecker.check(idCard);

        // then
        assertThat(valid).isFalse();
    }

    @Test
    @DisplayName("check - 담배를 구매할 수 있는 나이(20살)인 경우")
    void whenAgeIs20() {
        // given
        IdChecker idChecker = new IdChecker();
        IdCard idCard = new IdCard("sol", 20);

        // when
        boolean valid = idChecker.check(idCard);

        // then
        assertThat(valid).isTrue();
    }
}
