package com.example.mockito.part1.service;

import com.example.mockito.part1.domain.Account;
import com.example.mockito.part1.domain.Gym;
import com.example.mockito.part1.domain.GymRepository;

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
