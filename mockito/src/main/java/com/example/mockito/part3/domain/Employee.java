package com.example.mockito.part3.domain;

public class Employee {
    private final IdChecker idChecker;

    public Employee(IdChecker idChecker) {
        this.idChecker = idChecker;
    }

    public String sellCigar(IdCard idCard) {
        boolean valid = idChecker.check(idCard);

        if (!valid) {
            throw new RuntimeException("미성년자에게는 담배를 판매할 수 없습니다.");
        }

        return "담배";
    }
}
