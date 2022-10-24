package Bridge.example;

public class Draw {

    private final DrawImpl drawImpl;

    public Draw(DrawImpl draw) {
        this.drawImpl = draw;
    }

    public void draw() {
        this.drawImpl.draw();
    }
}
