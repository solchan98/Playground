package com.example.mockito.service.part1;

import com.example.mockito.domain.part1.Account;
import com.example.mockito.domain.part1.Gym;
import com.example.mockito.domain.part1.GymRepository;

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
