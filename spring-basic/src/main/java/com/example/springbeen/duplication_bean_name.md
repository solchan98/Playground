## 중복 Bean Name으로 등록
### 자동 설정 등록
컴포넌트 스캔을 통해 Bean이 등록된다.  
이때, Bean Name이 동일하다면 스프링은 Upsert 방식으로 등록하지 않고 `ConflictingBeanDefinitionException` 예외를 발생시킨다.

### 수동 설정 등록
만약, 자동 설정과 수동 설정을 통해 중복된 Bean Name이 등록된다면 이때는 스프링 부트 실행 시, 예외가 발생한다.  

즉, 테스트로 `ApplicationContext`를 만들어 올릴때는 예외는 발생하지 않고 아래의 로그를 남긴다.  
- ```
  Overriding bean definition for bean 'memoryMemberRepository' with a different
    definition: replacing
  ```
하지만, 스프링 부트를 올리면 다음과 같이 예외를 발생시킨다.  
- ```
  Consider renaming one of the beans or enabling overriding by setting
    spring.main.allow-bean-definition-overriding=true
  ```
이렇게 부트 실행 시, 예외를 발생시키는 이유는 Bean Name 충돌을 개발자가 의도하지 않았을 수 있으며 **개발자가 의도하지 않았지만 에러로 발생하지 않는다면 이 문제를 발견하기 매우 어렵기 때문**이다.
