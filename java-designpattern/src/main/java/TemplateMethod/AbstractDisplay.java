package TemplateMethod;

public abstract class AbstractDisplay {

    protected abstract void open();

    protected abstract void print();

    protected abstract void close();

    public final void display() {
        open();
        for (int idx = 0; idx < 5; idx++) {
            print();
        }
        close();
    }

}
