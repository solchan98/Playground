package com.example.mockito.service.part2;

import com.example.mockito.domain.part2.Customer;

public interface CustomerService {
    Customer findByName(String name);
}
