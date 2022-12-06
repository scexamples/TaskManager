package com.example.taskmanager;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "task_id")
	private int taskId;
	
	@Size(min = 5, max = 100, message = "Description must be between 5 and 100 characters")
	private String description;	
	
	@ManyToOne
    @JoinColumn(name="assigneeId", nullable=false)
	private Assignee assignee;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate completedDate;
	
	 
	public Task() {
		
	}
	
	public Task(String description, Assignee assignee, LocalDate createdDate, LocalDate dueDate,
			LocalDate completedDate) {
		super();
		this.description = description;
		this.assignee = assignee;
		this.createdDate = createdDate;
		this.dueDate = dueDate;
		this.completedDate = completedDate;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(LocalDate completedDate) {
		this.completedDate = completedDate;
	}

}
