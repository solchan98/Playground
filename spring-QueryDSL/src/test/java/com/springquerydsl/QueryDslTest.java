package com.springquerydsl;

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

        Store 이마트 = new Store("이마트");
        Store 롯데마트 = new Store("롯데마트");

        storeRepository.saveAll(List.of(이마트, 롯데마트));

        Item 치약_이마트 = new Item("치약", 3000L, 100L, 이마트);
        Item 치약_롯데마트 = new Item("치약", 3500L, 50L, 롯데마트);
        Item 우유_이마트 = new Item("우유", 1500L, 50L, 이마트);
        Item 우유_롯데마트 = new Item("우유", 1800L, 50L, 롯데마트);
        Item 귤_이마트 = new Item("귤", 15000L, 20L, 이마트);
        Item 귤_롯데마트 = new Item("귤", 12000L, 30L, 롯데마트);

        itemRepository.saveAll(List.of(치약_이마트, 치약_롯데마트, 우유_이마트, 우유_롯데마트, 귤_이마트, 귤_롯데마트));
    }

    @Test
    @DisplayName("기본 쿼리 - 기본 조회")
    void basic1 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .fetch();
        assertEquals(itemList.size(), 6);
    }

    @Test
    @DisplayName("기본 쿼리 - 기본 조건절")
    void basic2 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .where(item.quantity.goe(50L))
                .fetch();
        assertEquals(itemList.size(), 4);
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
        assertEquals(itemList.size(), 2);
    }

    @Test
    @DisplayName("기본 쿼리 - 정렬_ASC ")
    void basic4 () {
        List<Item> itemList = queryFactory
                .selectFrom(item)
                .orderBy(item.price.asc())
                .fetch();
        assertAll(
                () -> assertEquals(itemList.get(0).getName(), "우유"),
                () -> assertEquals(itemList.get(0).getStore().getName(), "이마트")
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
                () -> assertEquals(itemList.get(0).getName(), "귤"),
                () -> assertEquals(itemList.get(0).getStore().getName(), "이마트")
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
        assertEquals(itemList.size(), 1);
    }
}
