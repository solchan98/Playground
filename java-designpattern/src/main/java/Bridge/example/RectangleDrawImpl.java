package Bridge.example;

public class RectangleDrawImpl extends DrawImpl{

    private final static String type = "RECTANGLE";

    @Override
    public void draw() {
        System.out.println(type + " called draw!");
    }
}
