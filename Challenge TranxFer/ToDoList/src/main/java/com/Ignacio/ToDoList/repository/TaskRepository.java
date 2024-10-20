package com.Ignacio.ToDoList.repository;

import com.Ignacio.ToDoList.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.UUID;
@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByTitleContaining(String title);

}
