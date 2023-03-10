## TaskManager - a web app for task management

## Technologies
 Spring Boot  
 Spring Data JPA    
 MySQL  
 Thymeleaf  
 Bootstrap

## The app was designed for the following requirements:
- The application is used for task management. It reads a list of tasks from a relational data store and presents the list in a simple table.   
- A REST web service allows for querying, adding, updating and removing tasks.
- The project uses a MySQL Database and Spring Data JPA. Validation API is used to enforce validation constraints.

## Use Case  

### 1. Start the application and access on localhost:8080  

![img0landing](https://user-images.githubusercontent.com/15854708/206090912-923842cb-31eb-47a6-9dc8-969fdfc913f6.JPG)    

Page displays options to get details of a task by its id or to get details of all tasks in the db.     

### 2. Retreive a single task. For example, enter 3 in the task id input and click 'Find task'

![img2singletask](https://user-images.githubusercontent.com/15854708/206090925-3e7c6f80-b312-4950-8c07-e3af2449b475.JPG)    

##### Details of task id 3 are displayed.   

![img0landing](https://user-images.githubusercontent.com/15854708/206090912-923842cb-31eb-47a6-9dc8-969fdfc913f6.JPG)    

### 3. Retreive all tasks by clicking 'View all tasks'.  

![img3AllTasks](https://user-images.githubusercontent.com/15854708/206090934-addabebc-dfe4-47a7-ad15-a9cc8f8bae28.JPG)    

##### Details of all tasks are displayed with options to delete or update the task.   

### 4. For task id 5, Click 'Update'

![img5EditingTask5](https://user-images.githubusercontent.com/15854708/206090947-60142cd2-37b1-48f3-a62a-6ed031c4da4a.JPG)  

##### An editable form with details of task id 5 is displayed. Update fields and click 'Save'.  

![img6AfterEditingTask5](https://user-images.githubusercontent.com/15854708/206090961-65b4b72f-460b-422d-84ef-e8366818a0db.JPG)  

##### Details of all tasks are displayed. Task id 5 shows updated details.  

### 5. For task id 1, click 'Delete'.  

![img7DeletingTask1](https://user-images.githubusercontent.com/15854708/206090972-facafc28-2374-406a-899d-0e46c5c5878f.JPG)  

##### Details of all tasks are displayed. Task id 1 has been deleted.  

### 6. Click 'Add Task'.  
  
![img8AddTask](https://user-images.githubusercontent.com/15854708/206090990-0cbc12c3-e40d-4b65-a5a7-461f01f57509.JPG)  

##### Enter the task details and click 'Save'.  

![img8AfterAddingTask](https://user-images.githubusercontent.com/15854708/206091007-ce723009-3c68-426a-8c72-500f655b5f8e.JPG)  

##### Details of all tasks are displayed. The newly added task is listed as the last task in the list. 
