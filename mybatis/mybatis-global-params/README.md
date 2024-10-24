## 쓰레드 로컬을 활용하여 Mybatis 파라미터를 전역으로 활용하기

### 조건
1. `interface` 정의 시, `@Params()` 필히 적용
    - `mybatis interceptor`에서 파라미터를 `Map`으로 인지하여야 의도대로 동작
2. `interface` 파라미터로 `object` 전달 시, `xml`에서 필히 `#{object.***}` 형식으로 사용하기
    - 전역으로 세팅되는 파라미터와 구분하기 위함
    - 위 형식으로 미사용 시, 예외 발생