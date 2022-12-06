package com.example.taskmanager;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AssigneeRepository extends JpaRepository<Assignee, Integer> {
  
	public List<Assignee> findAll();
    
	public Optional<Assignee> findByAssigneeId(int assigneeId);
	
	@SuppressWarnings("unchecked")
	public Assignee save(Assignee assignee);
    
}
