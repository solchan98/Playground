package org.example.springsecurityjwt.access;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowable;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

class BearerAuthenticationConverterTest {

    BearerAuthenticationConverter bearerAuthenticationConverter = new BearerAuthenticationConverter();

    @Test
    @DisplayName("Authorization 헤더 필드가 없는 경우 예외")
    void convert_fail_when_not_exist_authorization_field() {
        // given
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        // when
        Throwable throwable = catchThrowable(() -> bearerAuthenticationConverter.convert(mockHttpServletRequest));

        // then
        assertThat(throwable).isInstanceOf(BadCredentialsException.class)
                .hasMessage("인증 정보를 확인하세요.");
    }

    @Test
    @DisplayName("Bearer 토큰 타입이 아닌 경우 예외")
    void convert_fail_when_not_formatted_to_bearer() {
        // given
        MockHttpServletRequest mockHttpServletRequest1 = new MockHttpServletRequest();
        mockHttpServletRequest1.addHeader("Authorization", "Basic abc");
        MockHttpServletRequest mockHttpServletRequest2 = new MockHttpServletRequest();
        mockHttpServletRequest2.addHeader("Authorization", "abc");

        // when
        Throwable throwable1 = catchThrowable(() -> bearerAuthenticationConverter.convert(mockHttpServletRequest1));
        Throwable throwable2 = catchThrowable(() -> bearerAuthenticationConverter.convert(mockHttpServletRequest1));

        // then
        assertThat(throwable1).isInstanceOf(BadCredentialsException.class)
                .hasMessage("인증 정보를 확인하세요.");
        assertThat(throwable2).isInstanceOf(BadCredentialsException.class)
                .hasMessage("인증 정보를 확인하세요.");
    }

    @ParameterizedTest
    @MethodSource(value = "sourceCovertSuccess")
    @DisplayName("Bearer 토큰을 성공적으로 헤더에서 추출한다.")
    void convert_success(MockHttpServletRequest request) {
        Authentication authentication = bearerAuthenticationConverter.convert(request);
        assertThat(authentication.getCredentials()).isEqualTo("abc");
    }

    static Stream<Arguments> sourceCovertSuccess() {
        MockHttpServletRequest mockHttpServletRequest1 = new MockHttpServletRequest();
        mockHttpServletRequest1.addHeader("Authorization", "bearer abc");
        MockHttpServletRequest mockHttpServletRequest2 = new MockHttpServletRequest();
        mockHttpServletRequest2.addHeader("Authorization", "Bearer abc");
        MockHttpServletRequest mockHttpServletRequest3 = new MockHttpServletRequest();
        mockHttpServletRequest3.addHeader("Authorization", "BeArEr abc");
        MockHttpServletRequest mockHttpServletRequest4 = new MockHttpServletRequest();
        mockHttpServletRequest4.addHeader("Authorization", "BEARER abc");

        return Stream.of(
                Arguments.of(mockHttpServletRequest1),
                Arguments.of(mockHttpServletRequest2),
                Arguments.of(mockHttpServletRequest3),
                Arguments.of(mockHttpServletRequest4)
        );
    }
}
