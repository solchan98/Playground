package com.example.mockito.service.part2;

import com.example.mockito.domain.part2.Customer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @Mock
    private CustomerService customerService;

    @Test
    @DisplayName("미성년자 담배 구매 - 기본 방식")
    void testBasic() {
        Customer customer = new Customer("철수", 16L);
        StoreService storeService = new StoreService(customerService);
        when(customerService.findByName("철수")).thenReturn(customer);
        String result = storeService.sellCigar("철수");
        assertEquals("미성년자에게는 판매할 수 없습니다.", result);
    }

    @Test
    @DisplayName("미성년자 담배 구매 - BDD 방식")
    void testBdd() {
        // given
        Customer customer = new Customer("철수", 16L);
        StoreService storeService = new StoreService(customerService);
        /// mocking
        given(customerService.findByName("철수")).willReturn(customer);

        // when
        String result = storeService.sellCigar("철수");

        // then
        assertEquals("미성년자에게는 판매할 수 없습니다.", result);
    }
    
}
