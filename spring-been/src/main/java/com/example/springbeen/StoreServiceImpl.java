package com.example.springbeen;

import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Override
    public boolean order(String foodName) {
        return true;
    }
}
