package com.springquerydsl.item;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ItemQueryDSLRepositoryImpl implements ItemQueryDSLRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Item findByNameAndPrice(String name, Long price) {
        return queryFactory
                .selectFrom(QItem.item)
                .where(QItem.item.name.eq(name))
                .where(QItem.item.price.eq(price))
                .fetchOne();
    }
}
