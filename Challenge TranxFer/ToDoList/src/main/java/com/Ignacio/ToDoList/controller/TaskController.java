package com.Ignacio.ToDoList.controller;

import com.Ignacio.ToDoList.entity.Task;
import com.Ignacio.ToDoList.repository.TaskRepository;
import com.Ignacio.ToDoList.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

   private final TaskService taskService;

    @PostMapping("/new")
    public ResponseEntity<Task> newTask(@RequestBody Task task){
        Task createdTask = taskService.createTask(task);
              return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
         }

    @GetMapping("/list")
    public ResponseEntity<List<Task>> listTasks(){
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }


    @GetMapping("/search/{title}")
    public ResponseEntity<List<Task>> getTaskByTitle(@PathVariable String title) {
        List<Task> tasks = taskService.findTasksByTitle(title);
        return ResponseEntity.ok(tasks);
    }


    @PutMapping("/edit/{id}")
    public  ResponseEntity<Task> editTask(@RequestBody Task task, @PathVariable UUID id){
        Task updatedTask = taskService.editTask(id , task);
            return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID id){
        taskService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully");
    }



}

 /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable UUID id){
        Optional<Task> findTask = taskService.findTaskById(id);

        if(findTask.isPresent()){

            taskService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully");

        }else{
            return ResponseEntity.notFound().build();
        }
    }*/
 /*@PutMapping("/edit/{id}")
    public  ResponseEntity<Task> editTask(@RequestBody Task task, @PathVariable UUID id){
        Task existingTask = taskService.findTaskById(id).orElse(null);

        if(existingTask == null){
            return ResponseEntity.notFound().build();
        }else{
            Task updatedTask = taskService.editTask(id, task);
            return ResponseEntity.ok(updatedTask);
        }
    }*/

/*@GetMapping("/search/{title}")
    public  ResponseEntity<List<Task>> getTaskByTitle(@PathVariable String title){
        List<Task> tasks = taskService.findTasksByTitle(title);

        if(tasks.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(tasks);
        }
    }*/