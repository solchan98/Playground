# 객체지향이론이란?

> 실제 세계는 사물(객체)로 이루어져 있으며, 발생하는 모든 사건들은 사물간의 상호작용이다.
실제 사물의 속성과 기능을 분석한 다음, 데이터(변수)와 함수로 정의하여 실제 세계를 컴퓨터 속에 옮겨 놓은 것과 같은 가상세계를 구현하고 이 가상세계에서 모의실험을 함으로써 많은 시간과 비용을 절약할 수 있다.  - 자바의 정석
>

## 객체지향언어의 특징

1. 코드의 재사용성이 높다.
    1. 새로운 코드를 작성할 때 기존의 코드를 이용하여 쉽게 작성할 수 있다.
2. 코드의 관리가 용이하다.
    1. 코드간의 관계를 이용해서 적은 노력으로 쉽게 코드를 변경할 수 있다.
3. 신뢰성이 높은 프로그램을 가능하게 한다.
    1. 제어자와 메서드를 이용해서 데이터를 보호하고 올바는 값을 유지하도록 하며, 코드의 중복을 제거하여 코드의 불일치로 인한 오작동을 방지할 수 있다.

## 클래스와 객체

흔히 클래스가 객체라고 착각하기 쉽다.

하지만 **클래스는 단지 객체를 생성하기 위해 사용될 뿐, 클래스와 객체는 같지 않다.**

용어로 정리해보면,

클래스는 객체를 정의해놓은 것, 객체의 설계도 혹은 틀이다.

객체는 실제로 존재하는 것이다.

즉, 객체는 클래스에 정의된 내용대로 메모리에 생성된 것을 뜻한다.

예로, 자동차 클래스와 객체를 보면 다음과 같다.

```java
// 자동차 클래스
class Car {
	String 브랜드;
	int 가격;
	String 이름;
}

// 자동차 객체
Car newCar; // Step 1
newCar = new Car(); // Step 2
/// 여기서 new Car는 Car 인스턴스를 생성하는 것이고, newCar참조변수는 생성된 인스턴스를 참조한다.
```

### 인스턴스와 참조변수

Step1

Car클래스 타입의 참조변수 newCar를 선언하였기 때문에 메모리에 공간이 생성된다.

Step2

new 연산자를 통해 Car클래스의 인스턴스가 생성되었고 브랜드는 참조형이므로 null로, 가격은 int형이므로 0으로, 이름은 참조형이므로 null로 초기화된다.

만약, 추가로 newCar2 참조변수와 Car인스턴스를 생성하고 newCar2참조변수가 newCar1의 인스턴스를 참조하게 만들면 어떻게 될까?

새로 만든 인스턴스는 더 이상 사용할 수 없게되고 newCar2 참조변수는 newCar1과 같은 인스턴스를 참조하게된다.

## 클래스변수(static)와 인스턴스변수

클래스변수와 인스턴스변수의 차이를 다루기 전 변수에 대해 기본적으로 다루어본다.

[변수의 종류와 특징](https://www.notion.so/f12293f9a1284c2382fc194f980cd4ca)

예제 클래스

```java
class 아이패드 {
	static String 브랜드 = "APPLE"; // 클래스 변수
	String 이름; // 인스턴스 변수
	public 아이패드(String 이름) { // 생성자 함수, new 할 때 초기화.
		this.이름 = 이름;
	}
}
```

아이패드 클래스를 가지고 아이패드 에어4세대와 아이패드 프로5세대를 만든다고 가정해보자.

```java
아이패드 air4 = new 아이패드("AIR4");
아이패드 pro5 = new 아이패드("PRO5");
```

두 개의 아이패드 인스턴스를 만들었다.

이 때, 아이패드의 클래스 변수와 인스턴스 변수를 보면 인스턴스 변수인 이름은 서로 다르다.

**인스턴스 변수는 해당 인스턴스만 소유하는 변수이기 때문이다.**

하지만, 두 인스턴스의 브랜드 변수는 동일하게 **"APPLE"**이다.

여기서 아래 코드와 같이 air4.브랜드 클래스 변수를 변경하면 **pro5의 브랜드 변수값도 "SAMSUNG"으로 변경**된다.

**이유는? 클래스 변수는 하나의 저장공간에 저장되고 해당 클래스로 만들어진 모든 인스턴스가 저장공간을 공유하기 때문이다.**

```java
air4.브랜드 = "SAMSUNG";
```

## 클래스메서드(static)와 인스턴스메서드

클래스메서드와 인스턴스메서드 또한 방금 다루었던 클래스변수와 인스턴스변수의 개념과 매우 흡사하다.

클래스메서드 또한 클래스변수처럼 클래스가 메모리에 올라갔을 때 생성된다.

인스턴스메서드 역시 인스턴스가 생성될 때 생성된다.

위 아이패드 클래스를 조금 수정해보자.

클래스변수인 브랜드를 반환해주는 get브랜드라는 클래스메서드를 생성하였다.

```java
class 아이패드 {
	static String 브랜드 = "APPLE"; // 클래스 변수
	String 이름; // 인스턴스 변수

	public 아이패드(String 이름) { // 생성자 함수, new 할 때 초기화.
		this.이름 = 이름;
	}

	static public String get브랜드() {
		return 브랜드;
	}
}

/// 실행코드
아이패드.get브랜드(); // 인스턴스 생성없이 get브랜드()메서드를 호출할 수 있다.
```

그러면 어떤 상황에서 클래스메서드를 쓰고, 쓰면 안될까?

1. 모든 인스턴스가 공통으로 사용하는 멤버변수에 static을 붙인다.
2. 작성한 메서드가 클래스변수와 클래스메서드만을 사용하는 경우.
3. **인스턴스변수나 인스턴스메서드를 사용하지 않는** 경우.

클래스메서드를 만들수 있다면 만드는 것이 좋다.

왜? **인스턴스메서드는 실행 시 메서드를 찾는 과정이 추가적으로 필요하여 시간이 더 걸리기 때문이다.**

## 클래스 멤버변수 초기화

멤버변수와 지역변수의 초기화 부분에서 차이점은 필수여부이다.

**멤버변수는 초기화를 하지 않아도** 자동적으로 변수의 **자료형에 맞는 기본값으로 초기화**가 되지만, **지역변수는 사용하기 전에 반드시 초기화**를 해야한다.

### 명시적 초기화

클래스의 멤벼변수 선언부에서 선언과 동시에 초기화를 진행할 수 있다.

이를 '명시적 초기화'라고 한다.

다음과 같이 사용된다.

```java
class Car {
	String name = "Model 3";
	int price = 60000000;
}
```

### 생성자

또 다른 방법으로는 생성자 함수를 통해 초기화가 가능하다.

생성자는 인스턴스를 초기화하는 메서드라고 생각하면 된다.

**생성자 메서드는 반환타입이 없고, 클래스의 이름이 메서드 명으로 사용된다.**

```java
class Car {
	String name;
	int price;
	public Car() {
		name = "Model 3";
		price = 60000000;
	}
}
```

**생성자 메서드를 호출할 때, 매개변수를 넘겨받아 초기화를 진행할 수 있다.**

여기서 this키워드를 볼 수 있는데, 'this.변수명'처럼 사용하면 매개변수와 인스턴스가 같을 때 구분할 수 있다.

즉, 'this.변수명'은 클래스의 인스턴스를 가리키는 것이다.

```java
class Car {
	String name;
	int price;
	public Car(String name, int price) {
		this.name = name;
		this.price = price;
}
```

### 초기화 블럭

초기화를 클래스가 처음 로딩될 때 와 인스턴스가 생성될 때 블럭 단위로 설정할 수 있다.

클래스 초기화 블럭은 static키워드와 블럭을 통해 작성이 가능하고, 인스턴스 초기화 블럭은 블럭을 통해 작성이 가능하다.

**클래스 초기화 블럭은 클래스 변수 초기화**

초기화 순서: 기본값 → 명시적초기화 → 클래스 초기화 블럭 → 생성자

**인스턴스 초기화 블럭은 인스턴스 변수 초기화**

초기화 순서: 기본값 → 명시적초기화 → 인스턴스 초기화 블럭 → 생성자

```java
class Car {
    public static String brand;
    public String name;
    public int price;
    /** 클래스 초기화 블럭 */
    static {
        brand = "Audi";
    }
    /** 인스턴스 초기화 블럭 */
    {
        name = "A7";
        price = 90000000;
    }
    public void sayMyInfo() {
        System.out.println("브랜드: " + brand);
        System.out.println("차종: " + this.name);
        System.out.println("가격: " + this.price);
    }
}

public class Main {

    public static void main(String[] args){
        Car car = new Car();
        car.sayMyInfo();
    }
}
```

---
[소스 링크](https://github.com/solchan98/java-playground/tree/main/java-basic/src/oop/basic)