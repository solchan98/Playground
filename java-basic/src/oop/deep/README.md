## 상속(~ing)

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
    - **point 새로 만든다!!!**
- 오버라이딩 - 상속받은 메서드의 내용을 변경하는 것.
    - **point 이미 있는 메서드를 수정한다!!!**

## 제어자

우리는 이전 기본 정리파트에서 public이라는 키워드를 본 적이 있었다.

앞에서 설명하진 않았지만, 그 만큼 클래스에서 필수적으로 사용이 된다는 뜻이다.

## 인터페이스

## 추상클래스와 내부클래스