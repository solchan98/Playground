package Bridge.example;

import org.junit.jupiter.api.Test;

public class ExampleTest {

    @Test
    void exampleTest() {
        CircleDrawImpl circleDrawImpl = new CircleDrawImpl();
        Draw circleDraw = new Draw(circleDrawImpl);
        CountDraw countRecDraw = new CountDraw(new RectangleDrawImpl());
        CountDraw countCircleDraw= new CountDraw(circleDrawImpl);


        circleDraw.draw();
        countRecDraw.multiDraw(2);
        countCircleDraw.multiDraw(3);
    }
}
