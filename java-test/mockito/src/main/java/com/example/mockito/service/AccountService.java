package com.example.mockito.service;

import com.example.mockito.domain.Account;

public interface AccountService {
    Account findByName(String name);
}
