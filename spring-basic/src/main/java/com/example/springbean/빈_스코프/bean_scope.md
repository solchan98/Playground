### 빈 스코프란?
  - 스프링 컨테이너가 시작된 후, 빈이 유지되고 존재할 수 있는 범위
### 빈 스코프 종류
  - 싱글톤
  - 프로토타입
  - 웹 계층
    - request
    - session
    - application

### 싱글톤
- Default 스코프 타입
- `스프링 컨테이너 시작 -> 종료` 까지 유지
- 따라서 빈 초기화(`@PostConstruct`) 및 소멸자(`@PreDestroy`) 정상 동작

### 프로토타입
- 스프링 컨테이너가 단순 생성 까지는 책임
  - 단순 빈 생성 및 초기화(`PostConstruct`) 동작까지만 관여
- 빈이 생성된 이후는 이를 호출한 곳에서 관리
- 스프링 컨테이너에 빈을 요청하면 매번 새로운 빈을 반환
  - 따라서 싱글톤 빈 + 프로토타입 빈 혼용 시, 발생하는 문제 존재

### 싱글톤 + 프로토타입 빈 혼용 문제
- 혼용 시, 문제 발생 케이스

```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Component
public class PrototypeBean {

}

@Service
public class SingletonService() {

    private final PrototypeBean prototypeBean;
    
    public SingletonService(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
    }
}
```

- 이때, `prototypeBean`은 싱글톤처럼 동작. 왜?
  - `SingletonService`가 단 한번 생성되면서 `PrototypeBean`이 생성되어 주입되기 때문
  - 따라서, 이미 주입된 `PrototypeBean`은 새로운 빈으로 바뀌지 않음
- 의도는 `PrototypeBean`이 매번 생성되기를 의도했으나 싱글톤으로 동작해버림

### 싱글톤 + 프로토타입 빈 혼용 문제 해결  
?