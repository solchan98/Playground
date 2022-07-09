package oop.deep;

class Overloading {
    public String name = "overloading";
    public void sayMyName() {
        System.out.println("나는 부모이고, 이름은 " + this.name);
    }
    public void sayMyName(int age) {
        System.out.println("나는 부모이고, 이름은 " + this.name + ", 나이는 " + age);
    }
}

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
