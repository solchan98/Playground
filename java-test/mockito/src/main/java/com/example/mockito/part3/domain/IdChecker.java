package com.example.mockito.part3.domain;

import com.example.mockito.part3.sdk.IdCardValidSDK;

public class IdChecker {
    private final int minAge = 20;

    private final IdCardValidSDK sdk;

    public IdChecker(IdCardValidSDK sdk) {
        this.sdk = sdk;
    }

    public boolean check(IdCard idCard) {
        if (idCard.getAge() < minAge) {
            return false;
        }

        return sdk.valid(idCard);
    }
}
