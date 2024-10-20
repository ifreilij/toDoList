package com.Ignacio.ToDoList.service;

import com.Ignacio.ToDoList.entity.Task;
import com.Ignacio.ToDoList.exception.TaskNotFoundException;
import com.Ignacio.ToDoList.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public Task createTask(Task task){

        return taskRepository.save(task);
    }
    public List<Task> getAllTasks(){
        List<Task> tasks = taskRepository.findAll();

        if(tasks.isEmpty()){
        throw new TaskNotFoundException("List is empty");
        }
        return tasks;
    }

    public List<Task> findTasksByTitle(String title) {
        List<Task> tasks = taskRepository.findByTitleContaining(title);

        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("No tasks found with the title: " + title);
        }

        return tasks;
    }
    public void deleteTask(UUID id){

        Task taskToDelete = taskRepository.findById(id)

                .orElseThrow(()-> new TaskNotFoundException("Task with id "+ id + " not found"));
        taskRepository.delete(taskToDelete);
    }

    public Task editTask(UUID id,Task task){
        Task taskToEdit = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("Task with id "+ id + " not found"));
        taskToEdit.setTitle(task.getTitle());
        taskToEdit.setDescription(task.getDescription());
        taskToEdit.setCompleted(task.isCompleted());
        return taskRepository.save(taskToEdit);
    }

}
