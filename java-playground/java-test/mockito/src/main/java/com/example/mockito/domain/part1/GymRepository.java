package com.example.mockito.domain.part1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GymRepository extends JpaRepository<Gym, Long> {
}
