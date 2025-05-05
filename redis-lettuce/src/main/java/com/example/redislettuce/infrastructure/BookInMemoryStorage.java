package com.example.redislettuce.infrastructure;

import com.example.redislettuce.domain.Book;

import java.util.HashMap;
import java.util.Map;

public class BookInMemoryStorage {

    private final Map<String, Book> books = new HashMap<>();

    public BookInMemoryStorage() {
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    public Book saveBook(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }
}
