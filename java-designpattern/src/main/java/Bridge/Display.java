package Bridge;

public class Display {

    private final DisplayImpl displayImpl;

    public Display(DisplayImpl displayImpl) {
        this.displayImpl = displayImpl;
    }

    public void start() {
        this.displayImpl.rawStart();
    }

    public void print() {
        this.displayImpl.rawPrint();
    }

    public void close() {
        this.displayImpl.rawClose();
    }

    public final void display() {
        this.start();
        this.print();
        this.close();
    }
}
