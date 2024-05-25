# 빈 생명주기
스프링 빈은 일반적으로 다음의 생명주기를 갖는다.
> 객체 생성 -> 의존관계 주입  

하지만, 생성자 주입은 객체 생성과 동시에 의존관계 주입이 진행된다.  

만약, 객체에 초기화 작업이 필요하다면?  
예) `DbClient`는 객체 생성 후, `URL, Username, Password` 등의 정보가 설정되어야 커넥션을 얻는 등 초기화 작업을 진행할 수 있다.  
따라서 모든 객체가 생성되고 의존관계 주입이 완료된 후, 초기화 작업을 진행해야 한다.  

그렇다면 의존관계 주입이 모두 완료된 시점을 어떻게 체크하지?  
> 스프링은 의존관계 주입이 완료되면 스프링 빈에 콜백 메서드를 호출하여 초기화 시점을 알려준다.  
> 
> 추가로 스프링 컨테이너가 종료되기 직전에 소멸 콜백 또한 호출한다.  

프로세스를 정리하면 다음과 같다.  
> 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> Run -> 소멸저 콜백 -> 스프링 종료  

여기서 질문, **그냥 초기화 콜백 없이 생성자 메서드에서 초기화 작업을 진행하면 안되는가?**  
객체의 책임 관점에서 논할 수 있다.  
생성자는 오로지 생성에 필요한 로직만 수행하여야 한다.  
관련 `Db Connection`을 맺는 과정이 생성자의 책임일까?  
커넥션을 맺는 과정은 생각보다 무거운 작업이며, 초기화 메서드의 책임으로 볼 수 있다.  
다만, 이 또한 트레이드 오프 영역이라 생각한다. 

## 인터페이스 방식(InitializeBean, DisposableBean)
```java
public class BeanLifeCycle1 implements InitializingBean, DisposableBean {
    
    @Override
    public void destroy() {
        // 커넥션 해제 진행
    }

    @Override
    public void afterPropertiesSet() {
        // 커넥션 연결 진행
    }
}
```

- 초기화, 소멸 메서드를 변경할 수 없다.
- 코드 수정이 불가능한 외부 라이브러리에 적용할 수 없다.
- 스프링 초창기에 나온 방식이며, 현재는 더 나은 방식으로 방법들이 있기에 자주 사용되지 않는다.
## 설정 정보에 초기화 및 소멸 메서드 지정
```java
public class BeanLifeCycle2 {

    public void init() {
        // 커넥션 해제 진행
    }

    public void destroy() {
        // 커넥션 연결 진행
    }
}

```
```java
@Configuration
public class BeanLifeCycle2Config {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanLifeCycle2 beanLifeCycle2() {
        return new BeanLifeCycle2();
    }
}
```
- 메서드 네이밍이 자유롭다.
- 코드가 아니라 설정 정보 사용하기 때문에 코드 수정이 불가능한 외부 라이브러리 또한 적용 가능하다.
## `@PostConstructor`, `@PreDestory` 어노테이션
```java
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanLifeCycle3 {

    @PostConstruct
    public void init() {
        // 커넥션 해제 진행
    }

    @PreDestroy
    public void destroy() {
        // 커넥션 연결 진행
    }
}
```
- `@PostConstruct, @PreDestroy`는 패키지 위치가 `import javax.annotation...`이다.
  - 따라서, 스프링이 아닌 다른 컨테이너를 사용하여도 적용 가능하다.
- 최신 스프링에서 권장하는 방법
- 외부 라이브러리에는 사용 불가능 하다.
  - 외부 라이브러리 코드에 직접 붙일 수 없기 때문이다. 
  - 이 경우에는 두번째 방식으로 진행한다.