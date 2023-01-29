package com.example.mockito.part3;

import com.example.mockito.part3.domain.IdCard;

public class IdCardValidSDK {
    private final Object connection;

    public IdCardValidSDK(Object connection) {
        this.connection = connection;
    }

    public boolean valid(IdCard idCard) {
        // TODO: 어떠한 외부 API 통신을 한다.
        // connection.valid(idCard);
        return false;
    }
}
