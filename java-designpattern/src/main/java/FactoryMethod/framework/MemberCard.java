package FactoryMethod.framework;

public class MemberCard extends Product{

    private final int serial;
    private final String owner;


    public MemberCard(int serial, String owner) {
        this.serial = serial;
        this.owner = owner;
        System.out.println(owner + "의 카드를 생성하였습니다.");
    }

    public void use() {
        System.out.println(owner + "의 카드를 사용하였습니다.");
    }

    public int getSerial() {
        return this.serial;
    }
}
