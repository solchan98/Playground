package com.example.mockito.domain.part2;

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
