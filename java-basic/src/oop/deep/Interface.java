package oop.deep;

interface Car {
    String maxSpeed = "200Km/h"; // public static final 생략(자동으로 들어감)
    void accelerate(); // public abstract 생략(자동으로 들어감)
}

class Basic implements Car {
    @Override
    public void accelerate() {
        // 나는 휘발류 or 경유 먹어
    }
}

class Electric implements Car {
    @Override
    public void accelerate() {
        // 나는 전기 먹오
    }
}

public class Interface {

}


/** SuperUser 만들기 시나리오 */
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
