## 상속

자바에서 상속은 매우 중요한 개념이다.

하지만 중요도에 비해 이해하기 어렵지 않다. 단순하게 다음과 같이 생각하면 된다.

**'상속이란 나의 복제인간을 만들되 나보다 뛰어난 기능을 추가하여 만든다'라고 생각하면 편하다.**

자동차를 예로 들어보자.

자동차는 액셀, 브레이크라는 공통의 기능이 있다.

그런데 자동차의 종류는 내연기관차, 자동차, 수소차 등등 종류가 많다.

클래스로 나타낸다면 **공통부분을 갖는 자동차는 부모 클래스,** 각자 **다른 특성을 갖는 나머지 종류들은 자식 클래스**가 된다.

자식클래스 작성시, extends 키워드를 사용해서 상속을 받는다.

추가로 단 하나의 클래스만 상속받을 수 있다.

위 시나리오대로 간단한 예제코드를 보면 다음과 같다.

1. class BasicCar **extends** Cars → Cars 부모클래스를 상속받는다.
2. super(...)를 통해 부모 클래스의 생성자를 호출한다.
3. 부모 클래스의 멤버변수가 아닌 자식 클래스의 멤버변수는 super(...)생성자 호출 이후 초기화를 진행한다.

현재 시나리오에서 볼 수 있는 가장 큰 장점은 **내연기관, 전기, 수소 클래스에 공통인 부분을 하나의 클래스로 뽑아내서 중복을 제거했다는 점**이다.

```java
/** 부모 클래스 */
class Cars {
    public String brand;
    public String name;
    public int price;
    public String type;

    public Cars(String brand, String name, int price, String type) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public void accelerate() {
        System.out.println("가속합니다.");
    }
    public void hitBrake() {
        System.out.println("감속합니다.");
    }
}

/** 자식 클래스 */
class BasicCar extends Cars {
    public String fuelGauge;
    public BasicCar(String brand, String name, int price, String type, String fuelGauge) {
        super(brand, name, price, type); // 부모 클래스의 생성자 호출
        this.fuelGauge = fuelGauge;
    }
}
/** 자식 클래스 */
class ElectricCar extends Cars {
    public String batteryCapacity;
    public ElectricCar(String brand, String name, int price, String type, String batteryCapacity) {
        super(brand, name, price, type); // 부모 클래스의 생성자 호출
        this.batteryCapacity = batteryCapacity;
    }
}
/** 자식 클래스 */
class HydrogenCar extends Cars {
    public String hydrogenTank;
    public HydrogenCar(String brand, String name, int price, String type, String hydrogenTank) {
        super(brand, name, price, type); // 부모 클래스의 생성자 호출
        this.hydrogenTank = hydrogenTank;
    }
}

public class Deep {
    public static void main(String[] args) {
        BasicCar basicCar = new BasicCar("Audi", "A7", 80000000, "Basic", "73L");
        ElectricCar electricCar = new ElectricCar("Tesla", "Model3", 60000000, "Electric", "50kWh");
        HydrogenCar hydrogenCar = new HydrogenCar("Hyundai", "Nexo", 70000000, "Hydrogen", "6.33kg");
    }
}
```

## 오버로딩과 오버라이딩

오버로딩은 상속과 상관은 없다.

오버라이딩과 비교를 진행하면서 다루기 위해 이번 파트에서 진행하였다.

### 오버로딩

오버로딩의 개념은 매우 간단하다.

**똑같은 이름의 다른 로직을 수행하는 메서드**를 만드는 것을 말한다.

어떻게 똑같은 이름의 메서드를 만들 수 있지?

메서드 호출시 넘기는 매개변수의 차이를 두어 오버로딩이 가능하다.

```java
오버로딩의 조건
1. 메서드 이름이 같아야 한다.
2. 매개변수의 개수 또는 타입이 달라야 한다.
```

아래 처럼 매개변수에 차이를 두어 서로 다른 메서드로 작동된다.

```java
class Overloading {
    public String name = "overloading";
    public void sayMyName() {
        System.out.println("이름: " + this.name);
    }
    public void sayMyName(int age) {
        System.out.println("이름: " + this.name + ", 나이: " + age);
    }
}

public class OverLoadingAndRiding {
    public static void main(String[] args) {
        Overloading overloading = new Overloading();
        overloading.sayMyName();
        overloading.sayMyName(999);
    }
}
```

### 오버라이딩

우리는 상속을 받으면 상속받은 부모의 멤버변수 및 메서드를 사용할 수 있다는 것을 알고있다.

만약, 부모의 메서드 이름을 그대로 사용하고 싶은데 수행되는 로직은 다르길 원한다면 어떻게 가능할까?

이 또한 매우 간단하게 가능하다.

부모의 메서드를 자식 클래스에 그대로 작성하되 수행되는 로직만 다르게 하면 된다.

```java
오버라이딩 조건
1. 부모클래스의 메서드와 이름이 같아야 한다.
2. 매개변수가 같아야 한다.(타입과 이름)
3. 반환타입이 같아야 한다.
```

아래와 같이 오버라이딩된다.

위에서 Overloading의 sayMyName()은 ("이름: " + this.name)을 출력하지만 오버라이딩 하였기 때문에 출력 결과는 "나는 자식이고, 이름은 overriding"이다.

```java
class Overriding extends Overloading {
    public Overriding() {
        this.name = "overriding";
    }
    @Override
    public void sayMyName() {
        System.out.println("나는 자식이고, 이름은 " + this.name);
    }
}

public class OverLoadingAndRiding {
    public static void main(String[] args) {
        Overriding overriding = new Overriding();
        overriding.sayMyName();
    }
}
```

**추가로 알면 좋은 내용!**

- 접근 제어자를 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다.
- 예외는 조상 클래스의 메서드보다 많이 선언할 수 없다.
- **static으로 선언된 메서드는 자식클래스에서 또 만들수 있지만 오버라이딩은 아니다.**

    ```java
    아래처럼 그냥 해당 클래스의 static메서드로 작성될 뿐이다.
    Overloading.sayMyName()
    Overriding.sayMyName()
    ```


### 오버로딩과 오버라이딩의 차이

오버로딩과 오버라이팅의 차이는 다음과 같다.

- 오버로딩 - 기존에 없는 새로운 메서드를 정의하는 것이다.
  - **새로 만든다!!!**
- 오버라이딩 - 상속받은 메서드의 내용을 변경하는 것.
  - **이미 있는 메서드를 수정한다!!!**

## 다형성

다형성이란 한 타입의 참조변수로 여러타입의 인스턴스를 참조할 수 있는 것을 말한다.

객체지향개념에서 상속과 마찬가지로 중요한 특징 중의 하나이다.

다형성을 사용하기위해 알아야 하는 여러가지 조건이 있지만, 다음의 내용만 기억한다면 당연한 것들이기 때문에 아래의 조건만 잘 기억하자.

1. 참조변수는 인스턴스 기준, 부모 혹은 본인의 타입이어야 한다.
  1. 인스턴스의 자식 클래스 타입은 ❌
2. 호출할 수 있는 메서드나 멤버변수는 참조변수 기준이다.
  1. 이전에 부모-자식 클래스는 포함관계라고 설명하였다.
  2. 사용 된 참조변수 기준, 즉 부모 클래스 기준이다.
3. 형변환은 참조변수 기준이며, Down-casting ❌

가전제품과 TV, 냉장고, 세탁기 시나리오를 통해 다형성을 확인해보자

```jsx
/** 가전제품 - 부모클래스 */
class Product {
    public String name;
    public int price;
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public void sayMyInfo() {
        System.out.println("명칭: " + this.name + ", 가격: " + this.price);
    }
}

/** TV - 자식클래스 */
class Tv extends Product {
    public int inch;
    public Tv(String name, int price, int inch) {
        super(name, price);
        this.inch = inch;
    }
}

/** 냉장고 - 자식클래스 */
class Fridge extends Product {
    public int capacity;
    public Fridge(String name, int price, int capacity) {
        super(name, price);
        this.capacity = capacity;
    }
}

/** 세탁기 - 자식클래스 */
class Washer extends Product {
    public String type;
    public Washer(String name, int price, String type) {
        super(name, price);
        this.type = type;
    }
}

public class Polymorphism {
    public static void main(String[] args) {
        Product tv = new Tv("4K", 5000000, 60);
        Product fridge = new Fridge("Bespoke", 1500000, 120);
        Product washer = new Washer("LG", 800000, "Drum");
        
        tv.sayMyInfo();
        fridge.sayMyInfo();
        washer.sayMyInfo();
    }
}
```

👆 코드를 보면 모든 자식 클래스의 **참조변수가 부모 클래스인 Product**이다.

따라서 자식 클래스의 멤버변수 및 메서드는 사용할 수 없고, 위 처럼 product의 멤버변수 및 메서드만 사용이 가능하다.

이렇게 사용하면, **자식 클래스의 메서드는 사용이 불가능한데 왜 이렇게 하는 것이죠?**

배열과 반복문을 통해 **공통의 로직을 하나의 코드로 작성하여 처리**할 수 있기 때문이다.

**즉, 여러 객체를 마치 하나의 객체처럼 다룰 수 있다.**

```jsx
public class Polymorphism {
    public static void main(String[] args) {
        Product tv = new Tv("4K", 5000000, 60);
        Product fridge = new Fridge("Bespoke", 1500000, 120);
        Product washer = new Washer("LG", 800000, "Drum");

        Product[] productList = new Product[]{tv, fridge, washer};
        for(Product product: productList) {
            product.sayMyInfo();
        }
    }
}
```

👆코드를 보면 배열과 반복문을 통해 코드를 간단하게 표현할 수 있다.

지금은 참조변수가 3개라 큰 차이가 없을지 모르지만, 만약 100개의  참조변수가 sayMyInfo()를 호출한다고 하면 100개의 코드 라인을 단 4줄로 표현할 수 있는 것이다.

## 인터페이스

인터페이스는 기능의 이름은 같지만 수행 로직이 다른 기능들을 구현이 아닌 정의만 해놓은 클래스라고 생각하면 된다.

물론 인터페이스에서도 멤버변수가 사용된다.

예로 자동차의 가속기능을 보면, 가속 기능이라는 이름은 모두 동일하지만 내연기관차 또는 전기차 또는 수소차 등 어떤 종류인지에 따라 **가속하는 과정 중 연료를 공급하는 과정**이 다 다르다.

따라서 가속 기능이라는 이름으로 정의만 하고, 상세 로직인 몸통은 구현하지 않는다.

```java
interface Car {
    String maxSpeed = "200Km/h"; // public static final 생략(자동으로 들어감)
    void accelerate(); // public abstract 생략(자동으로 들어감)
}

class Basic implements Car {
    @Override
    public void accelerate() {
				// 로직 구현
        // 나는 휘발류 or 경유 먹어
    }
}

class Electric implements Car {
    @Override
    public void accelerate() {
				// 로직 구현
        // 나는 전기 먹오
    }
}
```

👆 코드를 보면 Car 인터페이스는 maxSpeed라는 멤버변수를 정의 및 초기화 하고 sayMyInfo()라는 메서드를 정의하였다. 멤버변수는 선언과 동시에 초기화가 가능하지만, 메서드는 오로지 정의만 하고 구현은 실제 클래스에서 implements 하여 직접 구현한다.

### 인터페이스는 다중상속 가능

이전에 클래스는 단 하나의 상속만 가능하다고 했다.

하지만 인터페이스는 다르다.

다음의 시나리오로 확인해보자.

"슈퍼인간을 만들 예정이다. 이 슈퍼인간은 축구선수이며 농구선수도 하고 있고, 순간이동도 할 수 있다.

위 시나리오에서 클래스는 슈퍼인간이고 인터페이스는 축구선수와 농구선수, 순간이동 하는사람(텔로포터?)이다.

```java
interface FootballPlayer {
    void run();
    void shot();
}

interface Programmer {
    void likeCoffee();
		void googling();
}

interface Teleporter {
    void teleport();
}

class SuperUser implements FootballPlayer, Programmer, Teleporter {
    @Override
    public void run() {
        // 달린다.
    }

    @Override
    public void shot() {
        // 슛팅한다.
    }

    @Override
    public void likeCoffee() {
        // 나는 커피를 종아한다.(고 말하기)
    }

    @Override
    public void googling() {
        // 구글링을 한다.
    }

    @Override
    public void teleport() {
        // 순간이동 한다.
    }
}
```

👆 를 보면 SuperUser는 정의한 3개의 인터페이스를 모두 받아 구현하고 있는 것을 알 수 있다.

### 클래스처럼 참조변수를 인터페이스 타입으로 사용가능

클래스와 마찬가지로 인터페이스를 참조변수 타입으로 사용이 가능하다.

즉, 클래스처럼 다형성이 가능하여 활용이 가능하다.

```java
FootbalPlayer user = new SuperUser();
```

이렇게 FootbalPlayer 타입의 참조변수로 SuperUser인스턴스를 참조하면 다른 인터페이스의 기능인 likeCoffee(), teleport()를 호출할 수 있을까?

안된다.
클래스처럼 참조변수의 타입 기준으로 멤버변수 및 메서드에 대해 호출이 가능하다.

추가로, 만약 **서로 다른 인터페이스에서 같은 이름의 멤버변수를 정의 및 초기화** 하였다면 다음과 같이 사용한다.

```java
// 인터페이스.멤버변수
Programmer.name;
FootballPlayer.name;
```

---

+내부클래스 관련 내용 추가 포스팅하기