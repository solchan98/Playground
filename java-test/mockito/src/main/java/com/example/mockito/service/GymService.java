package com.example.mockito.service;

import com.example.mockito.domain.Account;
import com.example.mockito.domain.Gym;
import com.example.mockito.domain.GymRepository;

public class GymService {
    private final AccountService accountService;
    private final GymRepository gymRepository;

    public GymService(AccountService accountService, GymRepository gymRepository) {
        this.accountService = accountService;
        this.gymRepository = gymRepository;
    }

    public Gym addAccount(String name, Gym gym) {
        Account account = accountService.findByName(name);
        gym.setMember(account);
        return gymRepository.save(gym);
    }
}
