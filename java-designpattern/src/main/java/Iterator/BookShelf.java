package Iterator;

import java.util.ArrayList;
import java.util.List;

public class BookShelf implements Aggregate{
    private final List<Book> bookList;

    public BookShelf(int maxSize) {
        bookList = new ArrayList<>(maxSize);
    }

    public void appendBook(Book book) {
        this.bookList.add(book);
    }

    public Book getBook(int idx) {
        return this.bookList.get(idx);
    }

    public int getSize() {
        return this.bookList.size();
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}
