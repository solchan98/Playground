package com.springquerydsl.item;

public interface ItemQueryDSLRepository {
    Item findByNameAndPrice(String name, Long price);
}
