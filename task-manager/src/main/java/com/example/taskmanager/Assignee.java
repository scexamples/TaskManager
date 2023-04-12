package com.example.taskmanager;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Assignee")
public class Assignee {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "assignee_id")
	 private int assigneeId;
	 
	 @NotNull(message = "Name cannot be null")
	 private String name;
	 
	 @Email(message = "Email should be valid")
	 private String email;

	 @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = false)
	 private List<Task> tasks;
 
	public Assignee() {
		
	}

	public Assignee(String name, String email, List<Task> tasks) {
		super();
		this.name = name;
		this.email = email;
		this.tasks = tasks;
	}

	public int getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Task> getTasks() {
		return tasks;
	}

}