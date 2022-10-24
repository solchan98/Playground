package Bridge.example;

public class CountDraw extends Draw{

    public CountDraw(DrawImpl draw) {
        super(draw);
    }

    public void multiDraw(int cnt) {
        for (int idx = 0; idx < cnt; idx++) {
            draw();
        }
    }
}
