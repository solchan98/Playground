package com.example.redislettuce.infrastructure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CachedWithLock {
//    String prefix();         // 캐시 키 prefix
    long ttl() default 300;  // TTL (s)
    long lockWait() default 100; // 락 대기시간 (ms)
    long lockLease() default 5000; // 락 유지시간 (ms)
    long retryWait() default 100;; // 캐시 재조회 대기시간 (ms)
}
