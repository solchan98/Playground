package Iterator;

import org.junit.jupiter.api.Test;

public class IteratorTest {

    @Test
    void test() {
        BookShelf bookShelf = new BookShelf(10);

        for (int idx = 0; idx < 4; idx++) {
            bookShelf.appendBook(new Book("This is Book" + idx));
        }

        Iterator iterator = bookShelf.iterator();

        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.toString());
        }
    }
}
