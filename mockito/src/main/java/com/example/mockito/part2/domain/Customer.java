package com.example.mockito.part2.domain;

public class Customer {
    private String name;
    private Long age;

    public Customer(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    public Long getAge() {
        return this.age;
    }
}
