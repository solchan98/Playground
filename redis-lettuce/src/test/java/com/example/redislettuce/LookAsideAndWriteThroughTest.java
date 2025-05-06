package com.example.redislettuce;

import com.example.redislettuce.common.Cacheable;
import com.example.redislettuce.domain.Book;
import com.example.redislettuce.domain.BookRepository;
import com.example.redislettuce.infrastructure.BookInMemoryStorage;
import com.example.redislettuce.infrastructure.LookAsideAndWriteThroughBookRepository;
import com.example.redislettuce.infrastructure.CacheMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.mockito.Mockito.*;

@SpringBootTest
class LookAsideAndWriteThroughTest {

    @Autowired
    RedisTemplate<String, Cacheable> redisTemplate;

    BookInMemoryStorage spyBookInMemoryStorage = spy(new BookInMemoryStorage());

    @Autowired
    CacheMaster cacheMaster;


    @Test
    void lookAsideAndWriteThrough() {
        BookRepository bookRepository = new LookAsideAndWriteThroughBookRepository(spyBookInMemoryStorage, redisTemplate);

        Book book = new Book(
                "978-89-01-12345-6",
                "자바의 정석",
                "남궁성",
                "자바 언어의 기본부터 고급 내용까지 다루는 책",
                "https://example.com/image.jpg",
                "프로그래밍",
                32000,
                "도우출판",
                "2022-03-15"
        );

        bookRepository.save(book);

        /* Cache 조회 -> Cache Hit -> DB 조회 X */
        bookRepository.findByIsbn(book.getIsbn());
        verify(spyBookInMemoryStorage, times(0)).getBook(book.getIsbn());

        /* Cache Clear -> Cache Miss -> DB 조회 O */
        cacheMaster.clear(book.getIsbn());
        bookRepository.findByIsbn(book.getIsbn());
        verify(spyBookInMemoryStorage, times(1)).getBook(book.getIsbn());

        /* Cache 조회 -> Cache Hit -> DB 조회 X */
        bookRepository.findByIsbn(book.getIsbn());
        verify(spyBookInMemoryStorage, times(1)).getBook(book.getIsbn());
    }

}
