package com.example.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ConfigApplicationTest {

    /**
     * <pre>
     *     기대하는 memberRepository() 호출
     *      1. Config -> memberRepository()
     *      2. memberService() -> memberRepository()
     *      3. oriderService () -> memberRepository()
     *     하지만, memberRepository()는 총 1번만 호출된다.
     *
     *     `@Configuration`을 붙여 스프링이 바이트코드를 조작하여 실행됨
     *     실제 Config가 빈으로 등록되는 것이 아니라 CGLIB기술을 통해 Config를 상속받은 새로운 객체를 생성하여 활용
     *     새로운 객체는 빈 등록 여부에 따라 실제 호출을 분기처리 하는 것으로 예상
     * </pre>
     */
    @Test
    void testGetSingletonBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        MemberRepository memberRepository = applicationContext.getBean("memberRepository", MemberRepository.class);

        assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository())
                .isEqualTo(memberRepository);
    }
}
