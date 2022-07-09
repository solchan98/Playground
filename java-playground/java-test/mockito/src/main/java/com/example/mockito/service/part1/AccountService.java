package com.example.mockito.service.part1;

import com.example.mockito.domain.part1.Account;

public interface AccountService {
    Account findByName(String name);
}
