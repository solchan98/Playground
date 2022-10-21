package Iterator;

public class Book {

    private final String name;

    public Book(String name) {
        this.name = name;
    }

    private String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }
}
