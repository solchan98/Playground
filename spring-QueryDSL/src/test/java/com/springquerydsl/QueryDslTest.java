package com.springquerydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springquerydsl.item.Item;
import com.springquerydsl.item.ItemRepository;
import com.springquerydsl.store.QStore;
import com.springquerydsl.store.Store;
import com.springquerydsl.store.StoreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class QueryDslTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private JPAQueryFactory queryFactory;

    @Test
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
}
