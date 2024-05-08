### 자바 객체를 컴포넌트 스캔을 통해 빈으로 등록하고자하는 경우 아래의 어노테이션을 붙인다.  

`@Component`  

그 외 아래 처럼 여러 종류의 어노테이션으로 등록할 수 있다.  
아래 어노테이션들은 내부에 `@Component`가 정의되어 있다.
1. `@Controller`
    1. 스프링 MVC의 컨트롤러로 인식할 수 있게된다.
2. `@Repository`
    1. 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
    2. 즉, DB예외를 스프링의 예외로 변환하여 DB가 바뀌어도 동일한 예외로 처리 가능하다.
3. `@Configuration`
    1. 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리
       를 한다.
4. `@Service`
    1. `@Service`의 경우 특별한 부가 기능은 존재하지 않으며, 핵심 비즈니스가 모여있는 비즈니스 계층으로 인식할 수 있다.

참고로 자바에는 어노테이션 상속관계가 존재하지 않는다.  
즉, 위 어노테이션들이 내부에 `@Component`를 정의하였어도 자바 기능을 통해 제공되지는 않는다.  
이는 스프링이 지원하는 기능이다.

---

### Filter를 통해 등록 또는 제외하고싶은  빈을 필터 처리 할 수 있다.

```java
@ComponentScan(
        includeFilters = {
            @Filter(type = FilterType.ANNOTATION, classes = CustomAnnotation.class)
        },
        excludeFilters ={
            @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = HelloController.class)
        }
)
```

위 처럼 사용 가능하며, 방식은 아래와 같이 5개가 존재한다.  
1. ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.  
   ex) `org.example.SomeAnnotation`
2. ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.  
   ex) `org.example.SomeClass`
3. ASPECTJ: AspectJ 패턴 사용  
   ex) `org.example..*Service+`
4. REGEX: 정규 표현식  
   ex) `org\.example\.Default.*`
5. CUSTOM: `TypeFilter` 이라는 인터페이스를 구현해서 처리   
   ex) `org.example.MyTypeFilter`
      