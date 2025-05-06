package com.example.redislettuce.domain;

import java.util.Optional;

public interface BookRepository {

    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findByIsbnWithDistributedLock(String isbn);

    Book save(Book book);

}
