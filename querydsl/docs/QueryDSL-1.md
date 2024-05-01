# QueryDSL 사용해보기

## QueryDSL을 사용하게된 이유
지금까지 여러개의 ORM을 경험해봤다.  
처음 백엔드 개발을 Express로 시작하면서 **Sequlize**와 NestJs를 해보면서 **TypeORM**을 사용해봤다.  
Sequlize는 Query를 잘 몰라도 쉽게 사용이 가능했고 TypeORM은 Query를 어느 정도 잘 알면 효율적으로 사용이 가능했다.  
하지만 Spring을 시작하면서 ORM은 Query빌더를 접해보지도 않고 바로 JPA를 사용하였다.  
정확히 JPA는 ORM은 아니고 실제로 사용한 것도 SpringDataJPA이다.  
SpringDataJPA를 처음 접했을 때는 Query를 잘 작성하지 못해도 매우 편하게 데이터를 객체에 매핑하여 사용이 가능하여 정말 놀랐었다.  
하지만 호율적인 Query와 원하는 형식의 데이터를 받아 사용하고 싶은 욕구를 해결해주지는 못 하였다.  
추가로 Query가 복잡할수록 한 눈에 알아보기 힘든 메서드가 만들어졌다. 이 문제를 해결하기 위해 JPQL을 알게되었고 사용하다보니 결국 복잡한 Query는 QueryDSL로 해결하는 것이 좋다는 것을 알게되었다.  

## 초기 설정
QueryDSL은 Entity를 Q라는 클래스로 생성하여 사용하게 된다.  
따라서 초기 설정이 JPA 혹은 다른 것들에 비해 추가적인 작업이 필요하다.  
아래는 build.gradle이다.

```
// QueryDSL Version 5부터 추가가 필요하다고 한다.
// 최상단에 명시
buildscript {
	ext {
		queryDslVersion = "5.0.0"
	}
}

plugins {
	...
	id "com.ewerk.gradle.plugins.querydsl" version "1.0.10" // 작성
	...
}

...

// QueryDSL 설정 start
def querydslDir = "$buildDir/generated/querydsl"

dependencies {
	...
	// [QueryDSL]
	implementation "com.querydsl:querydsl-jpa:${queryDslVersion}"
	implementation "com.querydsl:querydsl-apt:${queryDslVersion}"
}

querydsl {
	jpa = true
	querydslSourcesDir = querydslDir
}
sourceSets {
	main.java.srcDir querydslDir
}
compileQuerydsl{
	options.annotationProcessorPath = configurations.querydsl
}
configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
	querydsl.extendsFrom compileClasspath
}
// QueryDSL 설정 end
```
일반적으로 dependencies에 추가만 하면 끝이지만 QueryDSL은 이렇게 비교적 많은 설정이 필요하다.

## Q클래스 생성하기
앞에서 언급했듯이 QueryDSL은 Entity를 Q클래스로 만들어서 사용한다.  
Q클래스를 만드는 법은 다음과 같다.  
1. QueryDSL 설정을 한다.
2. Entity를 작성한다.
3. Gradle -> Tasks -> other -> compileQuerydsl을 실행시킨다.  

위 과정을 정상적으로 진행하였다면 gradle에 설정한대로 build/generated에 Q클래스가 생성된 것을 확인할 수 있다.  

## 간단하게 사용해보기
기본적인 사용예시만 다룰 것이며 여러 예제는 [GitHub](https://github.com/solchan98/spring-playground/blob/main/spring-QueryDSL/src/test/java/com/springquerydsl/QueryDslTest.java)를 참고하면 확인할 수 있다.  

개인적으로 초기 설정만 마무리 했다면 사용하기는 오히려 SpringDataJPA보다 더 좋은 것 같다.  
일단, JpaQueryFactory를 컨테이너에 Bean으로 등록해야 한다. 이때 EntityManager이 필요하다.  
```java
@Configuration
@RequiredArgsConstructor
public class QueryDslConfig {
    private final EntityManager em;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
```
이제 jpaQueryFactory를 주입받아 사용하면 된다.  

가상의 Store와 Item이 있다고 가정하고 진행해보자.  
(Store와 Item은 1대다 관계이다.)

```java
@SpringBootTest
class Test {
    
    @Autowired
    private JpaQueryFactory queryFactory;
    
    @Test
    void basic() {
        Store store = queryFactory
                .selectFrom(QStore.store)
                .where(QStore.store.name.eq("이마트"))
                fetch();
        assertEquals(store.getName(), "이마트");
    }
}
```
Query를 아는 사람이라면 위 코드가 직관적으로 눈에 들어올 것이다.  
딱히 설명이 필요 없다. store.name.eq() 여기서 eq는 ==를 뜻 한다.  
goe는 greater or equal, lt는 less then이다.

기본적인 Query는 이렇게 표현 가능하고 좀 더 자세한 예제는 깃허브에 Test코드로 작성되어있다.  
그리고 SpringDataJPA를 사용할 때는 '사용방법을 자세히 알아야 효율적으로 사용이 가능하겠다.'라고 생각을 했었는데 QueryDSL은 사용법은 기본적으로 익히고 효율적으로 사용하기 위해서는 Query에 대한 지식이 중요하다고 생각이 된다.
그래서 요즘 Query에 대한 중요성을 크게 느끼고 공부를 하고 있다.
