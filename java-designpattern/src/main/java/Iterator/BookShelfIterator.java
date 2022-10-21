package Iterator;

public class BookShelfIterator implements Iterator {

    private final BookShelf bookShelf;

    private int idx;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.idx = 0;
    }

    @Override
    public boolean hasNext() {
        return idx < bookShelf.getSize();
    }

    @Override
    public Object next() {
        return bookShelf.getBook(idx++);
    }
}
