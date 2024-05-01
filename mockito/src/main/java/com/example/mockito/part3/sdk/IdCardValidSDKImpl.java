package com.example.mockito.part3.sdk;

import com.example.mockito.part3.domain.IdCard;

public class IdCardValidSDKImpl implements IdCardValidSDK {
    private final Object connection;

    IdCardValidSDKImpl(Object connection) {
        this.connection = connection;
    }

    @Override
    public boolean verify(IdCard idCard) {
        // NOTE: 어떠한 외부 API 통신을 한다.
        // NOTE : api를 1회 호출할 때 마다 0.01원이 부과된다.
//         connection.valid(idCard);
        return false;
    }

    @Override
    public void aMethod() {

    }

    @Override
    public void bMethod() {

    }

    @Override
    public void cMethod() {

    }

    @Override
    public void dMethod() {

    }

    @Override
    public void eMethod() {

    }
}
