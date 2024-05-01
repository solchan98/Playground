package com.example.mockito.part1.service;

import com.example.mockito.part1.domain.Account;

public interface AccountService {
    Account findByName(String name);
}
