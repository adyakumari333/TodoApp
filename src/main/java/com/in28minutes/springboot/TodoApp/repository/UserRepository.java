package com.in28minutes.springboot.TodoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.springboot.TodoApp.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  // Find user by username
}



