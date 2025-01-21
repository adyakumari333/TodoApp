package com.in28minutes.springboot.TodoApp.repository;

import com.in28minutes.springboot.TodoApp.model.Todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findByUsername(String username);
}

