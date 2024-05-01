package com.example.mockito.part3.domain;

public class IdCard {
    private final String name;
    private final int age;

    public IdCard(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
