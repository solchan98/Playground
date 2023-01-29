package com.example.mockito.part3;

import com.example.mockito.part3.domain.IdCard;
import com.example.mockito.part3.domain.IdChecker;
import com.example.mockito.part3.sdk.IdCardValidSDK;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("IdChecker Test")
public class IdCheckerTest {

    @Test
    @DisplayName("check - 담배를 구매할 수 없는 나이(19살)인 경우")
    void whenAgeIs19() {
        // given
        IdCard idCard = new IdCard("sol", 19);
        IdCardValidSDK idCardValidSDK = new IdCardValidSDK() {
            @Override
            public boolean valid(IdCard idCard) {
                return true;
            }

            @Override
            public void aMethod() {

            }

            @Override
            public void bMethod() {

            }

            @Override
            public void cMethod() {

            }

            @Override
            public void dMethod() {

            }

            @Override
            public void eMethod() {

            }
        };
        IdChecker idChecker = new IdChecker(idCardValidSDK);

        // when
        boolean valid = idChecker.check(idCard);

        // then
        assertThat(valid).isFalse();
    }

    @Test
    @DisplayName("check - 담배를 구매할 수 있는 나이(20살)인 경우")
    void whenAgeIs20() {
        // given
        IdCard idCard = new IdCard("sol", 20);
        IdCardValidSDK idCardValidSDK = new IdCardValidSDK() {
            @Override
            public boolean valid(IdCard idCard) {
                return true;
            }

            @Override
            public void aMethod() {

            }

            @Override
            public void bMethod() {

            }

            @Override
            public void cMethod() {

            }

            @Override
            public void dMethod() {

            }

            @Override
            public void eMethod() {

            }
        };
        IdChecker idChecker = new IdChecker(idCardValidSDK);

        // when
        boolean valid = idChecker.check(idCard);

        // then
        assertThat(valid).isTrue();
    }

    @Test
    @DisplayName("check - IdCard가 유효하지 않은 경우")
    void whenInvalidIdCard() {
        // given
        IdCard idCard = new IdCard("sol", 35);
        IdCardValidSDK idCardValidSDK = new IdCardValidSDK() {
            @Override
            public boolean valid(IdCard idCard) {
                return false;
            }

            @Override
            public void aMethod() {

            }

            @Override
            public void bMethod() {

            }

            @Override
            public void cMethod() {

            }

            @Override
            public void dMethod() {

            }

            @Override
            public void eMethod() {

            }
        };
        IdChecker idChecker = new IdChecker(idCardValidSDK);

        // when
        boolean valid = idChecker.check(idCard);

        // then
        assertThat(valid).isFalse();

    }
}
