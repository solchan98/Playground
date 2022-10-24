package Bridge;

public class CountDisplay extends Display{

    public CountDisplay(DisplayImpl displayImpl) {
        super(displayImpl);
    }

    public void multiDisplay(int cnt) {
        start();
        for (int idx = 0; idx < cnt; idx++) {
            print();
        }
        close();
    }
}
