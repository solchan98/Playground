package com.example.redislettuce;

import com.example.redislettuce.domain.Book;
import com.example.redislettuce.domain.BookRepository;
import com.example.redislettuce.infrastructure.BookInMemoryStorage;
import com.example.redislettuce.infrastructure.CacheMaster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;

@SpringBootTest
@EnableAspectJAutoProxy
class CacheStampedeTest {

    @Autowired
    BookRepository bookRepository;


    @Autowired
    CacheMaster cacheMaster;

    BookInMemoryStorage spyBookInMemoryStorage = spy(new BookInMemoryStorage());

    @BeforeEach
    void setUp() {
        // bookRepository 내부의 BookInMemoryStorage를 spy 객체로 교체
        ReflectionTestUtils.setField(bookRepository, "bookInMemoryStorage", spyBookInMemoryStorage);
    }

    /**
     * N개의 스레드 -> N번 read db and write cache
     */
    @Test
    void 캐시_폭주_확인() throws InterruptedException {
        int nThread = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(nThread);
        CountDownLatch doneLatch = new CountDownLatch(nThread);

        String isbn = saveNewBook(bookRepository);
        cacheClear(isbn);

        for (int i = 0; i < nThread; i++) {
            executorService.submit(() -> {
                bookRepository.findByIsbn(isbn);
                doneLatch.countDown();
            });
        }

        doneLatch.await();
        executorService.shutdown();

        verify(spyBookInMemoryStorage, times(nThread)).getBook(isbn);
    }

    /**
     * 분산락 적용 -> cache miss -> 해당 캐시 키로 lock
     * */
    @Test
    void 분산락_적용() throws InterruptedException {
        int nThread = 5;
        ExecutorService executorService = Executors.newFixedThreadPool(nThread);
        CountDownLatch doneLatch = new CountDownLatch(nThread);

        String isbn = saveNewBook(bookRepository);
        cacheClear(isbn);

        for (int i = 0; i < nThread; i++) {
            executorService.submit(() -> {
                bookRepository.findByIsbnWithDistributedLock(isbn);
                doneLatch.countDown();
            });
        }

        doneLatch.await();
        executorService.shutdown();

        verify(spyBookInMemoryStorage, times(1)).getBook(isbn);
    }

    private void cacheClear(String isbn) {
        cacheMaster.clear(isbn);
    }

    private static String saveNewBook(BookRepository bookRepository) {
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
        return book.getIsbn();
    }

}
