package oop.basic;

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

public class Basic {

    public static void main(String[] args){
        Car car = new Car();
        car.sayMyInfo();
    }
}
