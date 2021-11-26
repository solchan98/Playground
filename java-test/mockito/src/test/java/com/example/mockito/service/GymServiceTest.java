package com.example.mockito.service;

import com.example.mockito.domain.Account;
import com.example.mockito.domain.Gym;
import com.example.mockito.domain.GymRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GymServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private GymRepository gymRepository;

    @Test
    @DisplayName("mock()")
    void mockInMethod() {
        AccountService accountService = mock(AccountService.class);
        GymRepository gymRepository = mock(GymRepository.class);
        GymService gymService = new GymService(accountService, gymRepository);
        assertNotNull(gymService);
    }

    @Test
    @DisplayName("@Mock - In Field")
    void mockByAnnotationInField() {
        GymService gymService = new GymService(accountService, gymRepository);
        assertNotNull(gymService);
    }

    @Test
    @DisplayName("@Mock - In Method")
    void mockByAnnotationInMethod(@Mock AccountService accountService, @Mock GymRepository gymRepository) {
        GymService gymService = new GymService(accountService, gymRepository);
        assertNotNull(gymService);
    }

    @Test
    @DisplayName("헬스장에 회원 추가하기")
    void addMember() {
        Account account = new Account("SolChan", 24L);
        Gym gym = new Gym("헬스장");
        GymService gymService = new GymService(accountService, gymRepository);
        Mockito.when(accountService.findByName(any())).thenReturn(account);
        gymService.addAccount("SolChan", gym);
        assertEquals(account, gym.getMemberList().get(0));
    }
}