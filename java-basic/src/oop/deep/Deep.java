package oop.deep;

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
