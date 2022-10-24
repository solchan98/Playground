package Bridge.example;

public class CircleDrawImpl extends DrawImpl{

    private final static String type = "CIRCLE";

    @Override
    public void draw() {
        System.out.println(type + " called draw!");
    }
}
