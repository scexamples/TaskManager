package com.example.taskmanager;

import java.time.LocalDate;

//GET /tasks - list all tasks; optionally accept query parameters for pagination and filters 
//POST /tasks - create a new task 
//GET /tasks/ - retrieve details of the task identified by id 
//PUT /tasks/ - update the existing task identified by id 
//DELETE /tasks/ - delete the existing task identified by id The service MUST accept and return resources in JSON format. 
//Support for other formats, such as XML and HTML are optional. 
//Design the JSON schema you feel is appropriate given the existing data structure.

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskManagerController {
		
	@Autowired
	public AssigneeRepository assigneeRepository;
	
	@Autowired
	public TaskRepository taskRepository;
	
	
	/*
	 * Displays page offering option to retreive a task by id or all tasks from the db
	 */	
	@GetMapping("/")
    public String home(){

		return "index";
		
    }	
	
	/*
	 * Gets a task by input id
	 */
	@GetMapping("/tasks/taskId")
    public String getTask(Model model, @RequestParam int taskId){
		
		Optional<Task> optTask = taskRepository.findById(taskId);
		
		Task task = null;
		
		if(!optTask.isEmpty()) {
			task = optTask.get();
		} else {
			throw new RuntimeException("Task not found for taskId: " + taskId);
		}
		  	
	  	model.addAttribute("task", task);
	  	
        return "task";
    }
		
	/*
	 * Gets all tasks from the db
	 */	
	@GetMapping("/tasks")
    public String getAllTasks(Model model){
		
		List<Task> allTasks = taskRepository.findAll();
	  	
	  	model.addAttribute("allTasks", allTasks);
	  	
        return "tasks";
    }
	
	/*
	 * Displays a form to add details for a new task 
	 */
	@GetMapping("/addTask")
    public String addTask(Model model){
		
		List<Task> allTasks = taskRepository.findAll();
		
		model.addAttribute("allTasks", allTasks);
		
        return "add-task";
    }
	
	/*
	 * Creates a new task with submitted values, saves it to the db and displays all tasks from the db
	 */
	@PostMapping("/saveTask")
	public String createTask(@Valid @RequestBody MultiValueMap<String, String> formData) {
		
		String description = formData.getFirst("description");
		
		int assigneeId = Integer.parseInt(formData.getFirst("assigneeId"));
		Optional<Assignee> optAssignee = assigneeRepository.findByAssigneeId(assigneeId);		
		optAssignee.orElseThrow(() -> new RuntimeException("Assignee not found for assigneeId: " + assigneeId));
		
		LocalDate createdDate = null;
		String createdDateString = formData.getFirst("createdDate");
		if(!StringUtils.isBlank(createdDateString)) {
			createdDate = LocalDate.parse(createdDateString);
		}		
		
		LocalDate dueDate = null;
		String dueDateString = formData.getFirst("dueDate");
		if(!StringUtils.isBlank(dueDateString)) {
			dueDate = LocalDate.parse(dueDateString);
		}
				
		LocalDate completedDate = null;
		String completedDateString = formData.getFirst("completedDate");
		if(!StringUtils.isBlank(completedDateString)) {
			completedDate = LocalDate.parse(completedDateString);
		}
		
		Task task = new Task(description, optAssignee.get(), createdDate, dueDate, completedDate);
		
		taskRepository.saveAndFlush(task);
		
		return "redirect:/tasks"; // Cannot simply return "tasks" here -> need to fetch all tasks from db to first load to Model for display
	}
	
	/*
	 * Displays an editable form populated with details of the task to be updated
	 */
	@GetMapping("/edit/{taskId}")
    public String editTask(Model model, @PathVariable int taskId){

		Optional<Task> optTask = taskRepository.findById(taskId);
		
		Task task = null;
		
		if(!optTask.isEmpty()) {
			task = optTask.get();
		} else {
			throw new RuntimeException("Task not found for taskId: " + taskId);
		}
		
	  	model.addAttribute("task", task);
	  	
        return "edit-task";
    }
	
	/*
	 * Saves the updated task to the db
	 */
	@PostMapping("/updateTask/{taskId}")
    public String updateTask(@PathVariable int taskId, @Valid @RequestBody MultiValueMap<String, String> formData){

		Optional<Task> optTask = taskRepository.findById(taskId);
		
		Task task = null;
		
		if(!optTask.isEmpty()) {
			task = optTask.get();
		} else {
			throw new RuntimeException("Task not found for taskId: " + taskId);
		}
		
	//	formData.entrySet().forEach(e->System.out.println(e.getKey() + " " + e.getValue()));
		
		String description = formData.getFirst("description");		
		if(!StringUtils.isBlank(description)) {
			task.setDescription(formData.getFirst("description"));
		}
			
		String assigneeIdString = formData.getFirst("assigneeId");
		if(!StringUtils.isBlank(assigneeIdString)) {
			int assigneeId = Integer.parseInt(assigneeIdString);
			Optional<Assignee> optAssignee = assigneeRepository.findByAssigneeId(assigneeId);		
			optAssignee.orElseThrow(() -> new RuntimeException("Task not found for taskId: " + assigneeId));
			task.setAssignee(optAssignee.get());
		}
		
		String createdDate = formData.getFirst("createdDate");
		if(!StringUtils.isBlank(createdDate)) {
			task.setCreatedDate(LocalDate.parse(createdDate));
		}
				
		String dueDate = formData.getFirst("dueDate");
		if(!StringUtils.isBlank(dueDate)) {
			task.setDueDate(LocalDate.parse(dueDate));
		}
				
		String completedDate = formData.getFirst("completedDate");
		if(!StringUtils.isBlank(completedDate)) {
			task.setCompletedDate(LocalDate.parse(completedDate));
		}
		
		taskRepository.save(task);
		
		return "redirect:/tasks";
	
    }	

	/*
	 * Deletes the selected task
	 */
	@GetMapping("/delete/{taskId}")
    public String deleteTask(@PathVariable int taskId){
		
		Optional<Task> optTask = taskRepository.findById(taskId);
	
		optTask.orElseThrow(() -> new RuntimeException("Task does not exist for taskId: " + taskId));
		
		taskRepository.deleteById(taskId);
	  	
        return "redirect:/tasks";
    }

}
