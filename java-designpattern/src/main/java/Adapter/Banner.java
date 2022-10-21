package Adapter;

/**
 * 외부 클래스 역할
 * */
public class Banner {

    private final String content;

    public Banner(String content) {
        this.content = content;
    }

    public void showWithParen() {
        System.out.println("(" + content + ")");
    }

    public void showWithAster() {
        System.out.println("*" + content + "*");
    }
}
