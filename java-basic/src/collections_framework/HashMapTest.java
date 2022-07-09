package collections_framework;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, Car> carHashMap = new HashMap<>();

        Car 전기차 = new Car("전기차", 500000000L);
        Car 내연기관차 = new Car("내연기관차", 500000000L);
        carHashMap.put(전기차.name, 전기차);
        carHashMap.put(내연기관차.name, 내연기관차);
        System.out.println(carHashMap.size());
    }
}
