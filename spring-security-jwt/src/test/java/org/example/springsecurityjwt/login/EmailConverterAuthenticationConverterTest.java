package org.example.springsecurityjwt.login;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

class EmailConverterAuthenticationConverterTest {


    EmailPasswordAuthenticationConverter converter = new EmailPasswordAuthenticationConverter(new ObjectMapper());

    @Test
    @DisplayName("HttpServletRequest에서 email과 password가 담긴 UsernamePasswordAuthenticationToken을 얻는다.")
    void convert() {
        // given
        String email = "sol@sol.com";
        String password = "a1234567";
        String body = """
                {
                    "email": "%s",
                    "password": "%s"
                }
                """.formatted(email, password);
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setContent(body.getBytes(StandardCharsets.UTF_8));
        Authentication expected = new UsernamePasswordAuthenticationToken(
                email, password);

        // when then
        assertThat(converter.convert(mockHttpServletRequest)).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("로그인 요청 필드가 정상적이지 않으면 예외를 발생한다.")
    @MethodSource(value = "invalidBodies")
    void convert_fail_when_invalid_request(String body) {
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setContent(body.getBytes(StandardCharsets.UTF_8));

        // when
        Throwable throwable = catchThrowable(() -> converter.convert(mockHttpServletRequest));

        // then
        assertThat(throwable).isInstanceOf(InsufficientAuthenticationException.class)
                .hasMessage("로그인 요청 데이터를 확인하세요.");
    }

    static Stream<Arguments> invalidBodies() {
        return Stream.of(
                Arguments.of(
                        """
                                {
                                    "abc": "12asd"
                                }
                                """
                ),
                Arguments.of(
                        """
                                {
                                    "email": "sol@sol.com",
                                    "passw": "1234"
                                }
                                """
                ),
                Arguments.of(
                        """
                                {
                                    "email": "sol@sol.com",
                                    "passworld": ""
                                }
                                """
                ),
                Arguments.of(
                        """
                                {
                                    "email": "",
                                    "passworld": "1234"
                                }
                                """
                )
        );
    }
}
