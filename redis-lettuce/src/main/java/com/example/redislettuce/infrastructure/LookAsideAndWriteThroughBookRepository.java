package com.example.redislettuce.infrastructure;

import com.example.redislettuce.common.Cacheable;
import com.example.redislettuce.domain.Book;
import com.example.redislettuce.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LookAsideAndWriteThroughBookRepository implements BookRepository {

    private final BookInMemoryStorage bookInMemoryStorage;

    private final RedisTemplate<String, Cacheable> redisTemplate;

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        Cacheable value = redisTemplate.opsForValue().get(isbn);
        if (value instanceof Book book) {
            return Optional.of(book);
        }

        Book dbBook = bookInMemoryStorage.getBook(isbn);
        redisTemplate.opsForValue().set(isbn, dbBook);

        return Optional.of(dbBook);
    }

    @Override
    @CachedWithLock
    public Optional<Book> findByIsbnWithDistributedLock(String isbn) {
        return findByIsbn(isbn);
    }

    @Override
    public Book save(Book book) {
        Book dbBook = bookInMemoryStorage.saveBook(book);
        redisTemplate.opsForValue().set(book.getIsbn(), dbBook);
        return dbBook;
    }
}
