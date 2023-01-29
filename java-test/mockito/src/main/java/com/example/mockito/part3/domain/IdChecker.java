package com.example.mockito.part3.domain;

public class IdChecker {
    private final int minAge = 20;

    public boolean check(IdCard idCard) {
        if (idCard.getAge() < minAge) {
            return false;
        }

        return valid(idCard);
    }

    private boolean valid(IdCard idCard) {
        // TODO: 외부 API를 통해 idCard 검증
        return true;
    }
}
