package com.example.springjwt.domain.account.domain.dao;

import com.example.springjwt.domain.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByEmail(String email);
}
