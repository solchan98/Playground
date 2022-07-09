# Collections

Collections는 다수의 데이터를 의미하며, Framework는 표준화된 프로그래밍 방식을 의미한다.

이전에는 Vector, HashTable, Properties와 같은 다수의 데이터를 저장 및 처리하는 클래스를 따로 관리하였다. 이제는 Collections Framework를 통해 동일한 사용법을 통해 다양하고 풍부한 클래스를 다룰 수 있다.

쉽게 말하면 Vector와 List 참조변수가 있을 때, 데이터를 넣는 경우 동일하게 add메서드를 통해 넣을 수 있다.

컬렉션 프레임워크는 최상위 그룹으로 다음 3개의 인터페이스를 정의하였다.

**List, Set, Map**

여기서 공통의 기능을 갖는 List와 Set은 또 다시 Collection 인터페이스 하나로 묶어 정의되었다.

따라서 List와 Set은 Collection 인터페이스를 상속받는다.

각 컬렉션(List, Set, Map) 클래스의 원리와 사용법을 다루기 보다는 주로 사용하는 컬렉션 클래스를 선정하여 어떤 경우에 어떤 컬렉션 클래스의 사용이 좋은지 정리하였다.

## List

List는 중복하여 저장 가능하며, 저장 순서가 유지되는 컬렉션을 구현하는데 사용된다.

계층도는 다음과 같다.

```json
List
|--Vector
|--|--Stack
|--ArrayList
|--LinkedList
```

### ArrayList

ArrayList는 정적으로 생성하고 사용하는 배열을 동적으로 사용 가능하도록 제공하는 컬렉션 클래스이다.

```java
int[] numList = new int[5];
```

위 처럼 배열을 선언하면 numList는 5개 이상의 값을 저장할 수 없다.

```java
List<Integer> numList = new ArrayList<Integer>(5);
```

이 처럼 ArrayList로 선언하면 주어진 초기값 5로 배열을 생성한다.

이후 6개의 값을 넣으려고 하면 어떻게 될까?

아래 그림처럼 **기존 크기 2배의 크기를 갖는 배열을 새로 생성**하고 이 곳에 값을 모두 복사한다.

**ArrayList는 이 과정을 직접 하여 동적으로 배열의 크기를 조정할 수 있는 것 처럼 보이는 것이다.**

따라서 ArrayList를 사용할 때는 초기 선언 크기를 잘 고려해야 성능의 이점을 얻을 수 있다.

![asd](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/527f82af-a069-41b0-a3b7-d80900251824/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211211%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211211T065000Z&X-Amz-Expires=86400&X-Amz-Signature=bdaebbfc1ad417123dae25e793e012ef4ef0b51238393cab6ba26648abeb701c&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

### LinkedList

LinkedList는 여러 노드가 자신과 연결된 다음 노드의 주소값과 데이터로 저장되어 있다.

즉, 3번째 노드에 접근하려면 2번째 노드에 필수적으로 접근해야 한다.

만약 중간에 노드를 추가한다면, 예로 1번과 2번 사이에 새로운 노드 new노드를 넣는다면 1과 new를 연결하고 2와 new를 연결하면 끝이다. 하지만 순차적으로 추가하는 경우 노드의 맨 끝 까지 찾아가야 하는 시간이 필요하다.

그럼 ArrayList와 Linkedlist의 장단점을 이해했으니 다음의 상황에서는 어떤 컬렉션 클래스를 사용하는 것이 좋은지 생각해보자.

1. 순차적으로 추가/삭제하는 경우(ArrayList는 현재 크기가 꽉 찬 상태)
    1. LinkedList가 빠르다. ArrayList는 추가로 배열을 생성하고 복사하는 과정이 필요하기 때문이다.
    2. 만약, ArrayList 크기가 충분했다면 결과는 ArrayList가 더 빠르다.
        1. LinkedList는 맨 끝 까지 참조하는 과정이 필요하나 ArrayList는 그냥 남은 공간에 넣어주면 끝이다.
2. 중간에 데이터를 추가/삭제 하는 경우
    1. LinkedList가 빠르다. 예로 3노드를 삭제하는 경우라면 LinkedList는 2와 4 노드를 이어주고 3을 제거하면 끝이다. ArrayList는 4, 5...를 하나씩 위로 덮어 쓰는 처리를 해야하기 때문이다.

### 정리

| 컬렉션 | 읽기(Access Time) | 추가/ 삭제 | 예외 |
| --- | --- | --- | --- |
| ArrayList | 빠르다 | 느리다 | 순차적인 추가삭제는 더 빠르다. |
| LinkedList | 느리다 | 빠르다 | 데이터가 많을수록 접근성이 떨어진다. |

## Set

Set은 중복을 허용하지 않고, 순서가 보장되지 않는 컬렉션을 구현하는데 사용된다.

계층도는 다음과 같다.

```json
Set
|--HashSet
|--SrotedSet
|--|--TreeSet
```

### HashSet

HashSet은 대표적인 Set인터페이스를 구현한 컬렉션이다.

List와 다르게 중복을 허용하지 않고 저장순서를 보장하지 않는다.

따라서 이미 저장된 값을 저장하려고 하면 false를 반환한다.

만약 Car라는 클래스를 HashSet으로 저장하고 싶으면 어떻게 하면 될까?

그냥 HashSet를 만들어서 넣으면 될까? 아니다.

Car객체를 HashSet을 사용하기 위해선 equals()와 hashCode()를 오버라이딩 해야한다.

**왜? 어떤 기준으로 중복을 허용하지 않는지 정의해야 하기 때문이다.**

만약 다음 처럼 위 두 메서드를 오버라이딩 하지 않고 HashSet을 사용하면 중복되어 들어가 one, two의 값이 true로 출력될 것이다. 왜? 객체가 다르기 때문이다.

```java
HashSet<Car> carSet = new HashSet<Car>();
boolean one = carSet.add(new Car("전기차", 500000000L));
boolean two = carSet.add(new Car("전기차", 500000000L));
System.out.println(one + ", " + two);

class Car {
    private String name;
    private Long price;

    public Car(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
```

**아래 처럼 equals와 hashCode를 오버라이딩하여 Car의 name으로 중복을 허용하지 않을 수 있다.**

```java
class Car {
    private String name;
    private Long price;

    public Car(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
```

### TreeSet

TreeSet은 우리가 알고있는 이진트리 자료구조의 형태로 데이터를 저장하는 컬렉션 클래스이다.

일반 이진트리에서 성능이 향상된 Red-Black Tree로 구현되어있다.

Set인터페이스를 구현하여 중복을 허용하지 않고, 저장순서 또한 보장하지 않는다.

예로 7, 6, 8을 저장한다면 다음과 같은 형태로 저장이 된다.

즉, 처음 들어온 값이 root가 되고 다음 값과 비교하여 작으면 왼쪽 노드, 크면 오른쪽 노드로 들어간다.

![](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/74be45ff-b39c-4b3b-878b-2887447eae5b/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20211211%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20211211T065036Z&X-Amz-Expires=86400&X-Amz-Signature=ec71dde82d570dcaf9d55bd373caaab94b40b3de7fd4f33d04cbac8a287ad6e2&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22&x-id=GetObject)

**만약 객체를 저장한다면 HashSet처럼 비교 대상을 알려줘야 하기 때문에 Comparable을 구현하던가 Comparator를 제공해야한다.**

## Map

Map은 키와 값 하나의 쌍으로 저장되고 키의 중복은 허용하지 않는 컬렉션을 구현하는데 사용된다.

계층도는 다음과 같다.

```json
Map
|--Hashtable
|--HashMap
|--|--LinkedHashMap
|--SortedMap
|--|--TreeMap
```

### HashMap

HashMap은 키와 값 하나의 쌍으로 저장되는 컬렉션 클래스이다.

그리고 해싱을 사용하기 때문에 많은 양의 데이터를 검색하는데 있어서 뛰어난 성능을 보인다.

개인적으로 최근 가장 관심 갖고있는 자료구조이다.

DataBase에 데이터를 저장하면 보통 고유번호를 가지고 저장이 되는데, 이 고유번호를 Key로 사용하면 꽤 많은 부분에서 성능 최적화를 이루어낼 것 이라고 생각한다.

```java
HashMap<String, Car> carHashMap = new HashMap<>();
Car 전기차 = new Car("전기차", 500000000L);
Car 내연기관차 = new Car("내연기관차", 500000000L);
carHashMap.put(전기차.name, 전기차);
carHashMap.put(내연기관차.name, 내연기관차);
System.out.println(carHashMap.size());
```