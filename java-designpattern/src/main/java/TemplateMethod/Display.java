package TemplateMethod;

public interface Display {

    void open();

    void print();

    void close();

    default void display() {
        open();
        for (int idx = 0; idx < 5; idx++) {
            print();
        }
        close();
    }
}
