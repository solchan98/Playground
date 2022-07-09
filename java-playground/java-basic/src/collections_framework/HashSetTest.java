package collections_framework;

import java.util.HashSet;
import java.util.Objects;

public class HashSetTest {

    public static void main(String[] args) {
        HashSet<Car> carSet = new HashSet<Car>();
        boolean one = carSet.add(new Car("전기차", 500000000L));
        boolean two = carSet.add(new Car("전기차", 500000000L));
        System.out.println(one + ", " + two);
    }
}

class Car {
    public String name;
    public Long price;

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
