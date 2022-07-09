package com.example.mockito.service.part2;

import com.example.mockito.domain.part2.Customer;

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
