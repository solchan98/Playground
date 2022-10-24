package Bridge;

public class StringDisplayImpl extends DisplayImpl{

    private final String message;

    public StringDisplayImpl(String message) {
        this.message = message;
    }

    @Override
    public void rawStart() {
        System.out.println("Start String Display!");
    }

    @Override
    public void rawPrint() {
        System.out.println(this.message);
    }

    @Override
    public void rawClose() {
        System.out.println("Close String Display!");
    }
}
