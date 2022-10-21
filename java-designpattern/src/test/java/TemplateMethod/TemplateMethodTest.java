package TemplateMethod;

import org.junit.jupiter.api.Test;

public class TemplateMethodTest {

    /**
     * Interface의 Display와 AbstractClass의 AbstractDisplay의 차이는?
     * 추상 클래스로 만든 display()는 final 키워드로 인해 하위 클래스에서 오버라이딩이 불가능하다.
     * GoF 책에 의하면 템플릿 메소드의 경우 오버라이딩 하지 말아야 한다고 적혀있다고 함.
     * */

    @Test
    void templateMethodTest() {
        Display charDisplay = new CharDisplay('H');
        AbstractDisplay stringDisplay = new StringDisplay("Hello, world.");

        charDisplay.display();
        stringDisplay.display();
    }
}
