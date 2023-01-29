package com.example.mockito.part3.sdk;

public final class IdCardSDKManager {
    private IdCardSDKManager() {}
    public static IdCardValidSDKImpl createSDK() {
        return new IdCardValidSDKImpl(new Object());
    }
}
