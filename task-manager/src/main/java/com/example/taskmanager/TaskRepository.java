package com.example.taskmanager;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Integer> {
   
	public List<Task> findAll();
    
	public Optional<Task> findById(int taskId);

}
