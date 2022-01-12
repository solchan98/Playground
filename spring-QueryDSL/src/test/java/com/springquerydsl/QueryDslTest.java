package com.springquerydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springquerydsl.item.Item;
import com.springquerydsl.item.ItemRepository;
import com.springquerydsl.store.Store;
import com.springquerydsl.store.StoreRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static com.springquerydsl.item.QItem.item;
import static com.springquerydsl.store.QStore.store;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QueryDslTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private JPAQueryFactory queryFactory;

    @Test
    @Disabled
    @DisplayName("데이터 넣기")
    void init() {

        Store 이마트 = new Store("이마트", 40L);
        Store 롯데마트 = new Store("롯데마트", 70L);

        storeRepository.saveAll(List.of(이마트, 롯데마트));

        Item 치약_이마트 = new Item("치약", 3000L, 100L, 이마트);
        Item 치약_롯데마트 = new Item("치약", 3500L, 50L, 롯데마트);
        Item 우유_이마트 = new Item("우유", 1500L, 50L, 이마트);
        Item 우유_롯데마트 = new Item("우유", 1800L, 50L, 롯데마트);
        Item 귤_이마트 = new Item("귤", 15000L, 20L, 이마트);
        Item 귤_롯데마트 = new Item("귤", 12000L, 30L, 롯데마트);

        itemRepository.saveAll(List.of(치약_이마트, 치약_롯데마트, 우유_이마트, 우유_롯데마트, 귤_이마트, 귤_롯데마트));
    }

    /** QueryDSL-1 */
    @Test
    @DisplayName("기본 쿼리 - 기본 조회")
    void basic1 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .fetch();
        assertEquals(6, itemList.size());
    }

    @Test
    @DisplayName("기본 쿼리 - 기본 조건절")
    void basic2 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .where(item.quantity.goe(50L))
                .fetch();
        assertEquals(4, itemList.size());
    }

    @Test
    @DisplayName("기본 쿼리 - 다중 조건절 ")
    void basic3 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .where(
                        item.quantity.goe(50L),
                        item.name.contains("약"),
                        item.price.loe(5000L)
                )
                .fetch();
        assertEquals(2, itemList.size());
    }

    @Test
    @DisplayName("기본 쿼리 - 정렬_ASC ")
    void basic4 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .orderBy(item.price.asc())
                .fetch();
        assertAll(
                () -> assertEquals("우유", itemList.get(0).getName()),
                () -> assertEquals("이마트", itemList.get(0).getStore().getName())
        );
    }

    @Test
    @DisplayName("기본 쿼리 - 정렬_DESC ")
    void basic5 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .orderBy(item.price.desc())
                .fetch();
        assertAll(
                () -> assertEquals("귤", itemList.get(0).getName()),
                () -> assertEquals("이마트", itemList.get(0).getStore().getName())
        );
    }

    @Test
    @DisplayName("기본 쿼리 - 페이징 ")
    void basic6 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .offset(5) // 시작 위치 | 초기화 한 Item 개수는 6개
                .limit(2) // 가져올 개수
                .fetch();
        assertEquals(1, itemList.size());
    }

    /** QueryDSL-2 */
    // EQUI JOIN
    @Test
    @DisplayName("조인 - EQUI JOIN")
    void join1() {
        List<Item> result = queryFactory
                .select(item)
                .from(store, item)
                .where(store.eq(item.store))
                .fetch();

        result.forEach(System.out::println);
        assertEquals(6, result.size());
        /* 예상 결과
            Item{id=1, name='치약', price=3000, quantity=100, store=com.springquerydsl.store.Store@24fb752f}
            Item{id=2, name='치약', price=3500, quantity=50, store=com.springquerydsl.store.Store@47cf65f1}
            Item{id=3, name='우유', price=1500, quantity=50, store=com.springquerydsl.store.Store@24fb752f}
            Item{id=4, name='우유', price=1800, quantity=50, store=com.springquerydsl.store.Store@47cf65f1}
            Item{id=5, name='귤', price=15000, quantity=20, store=com.springquerydsl.store.Store@24fb752f}
            Item{id=6, name='귤', price=12000, quantity=30, store=com.springquerydsl.store.Store@47cf65f1}
         */
    }

    // INNER JOIN
    @Test
    @DisplayName("조인 - INNER JOIN")
    void join2() {
        List<Item> result = queryFactory
                .selectFrom(item)
                .join(item.store, store)
                .fetchJoin()
                .fetch();
        assertEquals(6, result.size());
        /* 예상 결과
            Item{id=1, name='치약', price=3000, quantity=100, store=com.springquerydsl.store.Store@24fb752f}
            Item{id=2, name='치약', price=3500, quantity=50, store=com.springquerydsl.store.Store@47cf65f1}
            Item{id=3, name='우유', price=1500, quantity=50, store=com.springquerydsl.store.Store@24fb752f}
            Item{id=4, name='우유', price=1800, quantity=50, store=com.springquerydsl.store.Store@47cf65f1}
            Item{id=5, name='귤', price=15000, quantity=20, store=com.springquerydsl.store.Store@24fb752f}
            Item{id=6, name='귤', price=12000, quantity=30, store=com.springquerydsl.store.Store@47cf65f1}
         */
    }

    // INNER JOIN ON
    @Test
    @DisplayName("조인 - JOIN ON")
    void join3() {
        List<Item> result = queryFactory
                .select(item)
                .from(item)
                .join(store).on(store.eq(item.store))
                .fetchJoin()
                .fetch();
        assertEquals(6, result.size());
        result.forEach(System.out::println);
    }

    /// Tuple 타입 설명 포스팅에 추가하기

    // LEFT JOIN
    @Test
    @DisplayName("조인 - LEFT JOIN")
    void join4() {
        List<Tuple> result = queryFactory
                .select(item, store)
                .from(item)
                .leftJoin(store).on(store.score.lt(item.quantity))
                .fetch();
        result.forEach(System.out::println);
        assertEquals(7, result.size());
        /*
            [Item{id=7, name='치약', price=3000, quantity=100, store=Store{id=3, name='이마트}}, Store{id=3, name='이마트}]
            [Item{id=7, name='치약', price=3000, quantity=100, store=Store{id=3, name='이마트}}, Store{id=4, name='롯데마트}]
            [Item{id=8, name='치약', price=3500, quantity=50, store=Store{id=4, name='롯데마트}}, Store{id=3, name='이마트}]
            [Item{id=9, name='우유', price=1500, quantity=50, store=Store{id=3, name='이마트}}, Store{id=3, name='이마트}]
            [Item{id=10, name='우유', price=1800, quantity=50, store=Store{id=4, name='롯데마트}}, Store{id=3, name='이마트}]
            [Item{id=11, name='귤', price=15000, quantity=20, store=Store{id=3, name='이마트}}, null]
            [Item{id=12, name='귤', price=12000, quantity=30, store=Store{id=4, name='롯데마트}}, null]
        */
    }

    // SubQuery
    @Test
    @DisplayName("서브쿼리 - Where 절")
    void subQuery() {
        List<Item> result = queryFactory
                .selectFrom(item)
                .where(item.quantity.lt(
                        JPAExpressions
                                .select(store.score.max()) // 70L
                                .from(store)
                ))
                .fetch();
        assertEquals(5, result.size());
    }

    // Case
    @Test
    @DisplayName("CASE문 - CASE")
    void caseQuery() {
        List<String> result = queryFactory
                .select(
                        new CaseBuilder().when(item.price.lt(2000L)).then("싸")
                                .when(item.price.lt(10000L)).then("적당해")
                                .otherwise("비싸")
                ).
                from(item)
                .fetch();
        assertAll(
                () -> assertEquals(2, result.stream().filter((str) -> Objects.equals(str, "싸")).count()),
                () -> assertEquals(2, result.stream().filter((str) -> Objects.equals(str, "적당해")).count()),
                () -> assertEquals(2, result.stream().filter((str) -> Objects.equals(str, "비싸")).count())
        );
    }
}
