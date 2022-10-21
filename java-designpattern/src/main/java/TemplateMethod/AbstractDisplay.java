package TemplateMethod;

public abstract class AbstractDisplay {

    public abstract void open();

    public abstract void print();

    public abstract void close();

    public final void display() {
        open();
        for (int idx = 0; idx < 5; idx++) {
            print();
        }
        close();
    }

}
