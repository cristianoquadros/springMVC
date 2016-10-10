package com.crossover.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crossover.domain.User;

public interface UserRepository extends JpaRepository<User, String> {
}