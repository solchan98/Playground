package com.example.mockito.part2.service;

import com.example.mockito.part2.domain.Customer;

public interface CustomerService {
    Customer findByName(String name);
}
