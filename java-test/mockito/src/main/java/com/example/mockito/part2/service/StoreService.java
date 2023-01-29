package com.example.mockito.part2.service;

import com.example.mockito.part2.domain.Customer;

public class StoreService {

    private final CustomerService customerService;
    public StoreService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String sellCigar(String name) {
        Customer customer = customerService.findByName(name);
        if(customer.getAge() < 20) {
            return "미성년자에게는 판매할 수 없습니다.";
        }
        return "Cigar";
    }
}
