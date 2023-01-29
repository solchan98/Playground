package com.example.mockito.part1.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GymRepository extends JpaRepository<Gym, Long> {
}
