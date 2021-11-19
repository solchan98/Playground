package oop.deep;

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

//        tv.sayMyInfo();
//        fridge.sayMyInfo();
//        washer.sayMyInfo();

        // 배열 및 반복문 처리
        Product[] productList = new Product[]{tv, fridge, washer};
        for(Product product: productList) {
            product.sayMyInfo();
        }
    }
}
