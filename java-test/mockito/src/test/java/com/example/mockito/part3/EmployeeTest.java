package com.example.mockito.part3;

import com.example.mockito.part3.domain.Employee;
import com.example.mockito.part3.domain.IdCard;
import com.example.mockito.part3.domain.IdChecker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@DisplayName("Employee Test")
@ExtendWith(MockitoExtension.class)
public class EmployeeTest {

    @InjectMocks
    private Employee employee;

    @Mock
    IdChecker idChecker;

    @Test
    @DisplayName("sellCigar - 담배 구매 불가, 따라서 예외 발생")
    void whenAgeIs19() {
        // given
        IdCard idCard = new IdCard("sol", 19);
        given(idChecker.check(idCard)).willReturn(Boolean.FALSE);

        // when
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> employee.sellCigar(idCard)
        );

        // then
        assertThat(exception.getMessage()).isEqualTo("미성년자에게는 담배를 판매할 수 없습니다.");
    }

    @Test
    @DisplayName("sellCigar - 담배 구매 가능")
    void whenAgeIs20() {
        // given
        IdCard idCard = new IdCard("sol", 20);
        given(idChecker.check(idCard)).willReturn(Boolean.TRUE);

        // when
        String cigar = employee.sellCigar(idCard);

        // then
        assertThat(cigar).isEqualTo("담배");
    }
}
