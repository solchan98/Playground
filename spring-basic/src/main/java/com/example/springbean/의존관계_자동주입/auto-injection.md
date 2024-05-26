# 스프링의 의존관계 지동 주입 방식

### 생성자 주입 방식

```java

import com.example.springbean.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private final StoreService storeService;

    @Autowired
    public Service(StoreService storeService) {
        this.storeService = storeService;
    }
}
```
- 생성자를 통해 의존관계를 주입 받는다.
- 생성자 호출 시에 동작하기에 딱 1번 호출을 보장한다.
- 1번만 호출되기 때문이 불변을 보장한다.
- 생성자가 1개만 존재한다면 `@Autowired` 생략이 가능하다.

### 수정자 주입

```java
import com.example.springbean.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private StoreService storeService;

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }
}
```
- setter 메서드를 통해 의존관계를 주입 받는다.
- 변경이 필요한 경우 사용이 된다.
- 생성자 주입과 다르게 불변을 보장할 수 없다.

### 필드 주입

```java
import com.example.springbean.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

    @Autowired
    private StoreService storeService;
}
```
- 단순 필드 정의로 구현이 가능하다.
- DI 프레임워크 없이 동작할 수 없다.
- 외부에서 변경이 불가능하여 테스트 하기 매우 어렵다.

### 여러 구현체를 한번에 주입 받기

```java
import com.example.springbean.StoreService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private final List<StoreService> storeServices;

    private final Map<String, StoreService> storeServiceMap;

    @Autowired
    public Service(List<StoreService> storeServices, Map<String, StoreService> storeServiceMap) {
        this.storeServices = storeServices;
        this.storeServiceMap = storeServiceMap;
    }
}
```
- 동일한 인터페이스를 구현한 모든 구현체를 주입받을 수 있다.
- Map의 경우 key로 해당 빈 네임이 들어온다.

### 추천하는 주입 방식
일반적으로 필드 주입은 권고하지 않는다.  
위에서도 언급했듯이 DI 프레임워크 없이 동작할 수 없으며, 테스트하기 매우 어렵다는 명백한 단점을 보이고 있다.

수정자 주입 또한 불변을 보장하지 않기 때문에 일반적으로 권고하지 않는다.  
변경 가능성이 있어도 생성자 주입을 통해 해결 가능하다.

**따라서 생성자 주입 방식을 권고한다.**  
우선, 생성자 주입 방식은 위 두 방식의 단점을 모두 해결한다.  
그리고 불변을 보장한다.  
만약, 인터페이스를 구현한 여러 구현체를 모두 사용해야 한다면 List와 Map으로 주입받아 활용할 수 있다.

### 여러 구현체 중 특정 빈 주입 받기

```java
import com.example.springbean.StoreService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("storeServiceImpl")
public class StoreServiceImpl implements StoreService {

}
```
#### `@Autowired`

```java
import com.example.springbean.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private final StoreService storeServiceImpl;

    @Autowired
    public Service(StoreService storeServiceImpl) {
        this.storeServiceImpl = storeServiceImpl;
    }
}
```
1. 인터페이스를 구현한 구현체를 찾는다.
2. 구현체가 2개 이상인 경우, 필드 명과 파리미터 명으로 찾는다. 


#### `@Qualifier`

```java
import com.example.springbean.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Service {

    private final StoreService storeService;

    @Autowired
    public Service(@Qualifier("storeServiceImpl") StoreService storeService) {
        this.storeService = storeService;
    }
}
```
1. `@Qualifier("storeServiceImpl")`이 붙은 빈을 찾는다.
2. Bean Name이 "StoreServiceImpl"인 빈을 찾는다.
3. 빈을 찾지 못했다는 `NoSuchBeanDefinitionException` 예외가 발생한다.