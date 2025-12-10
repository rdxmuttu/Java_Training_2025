package com.ust.demo.controller;

import com.ust.demo.entity.Task;
import com.ust.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")  // Base URL for all task-related requests
public class TaskController {

    @Autowired
    private TaskService taskService;  // Injecting the TaskService

    // Get tasks by the assignee ID
    @GetMapping
    public ResponseEntity<List<Task>> getTasksByAssignee(@RequestParam Long assigneeId) {
        List<Task> tasks = taskService.getTasksByAssignee(assigneeId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Get a specific task by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // Create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    // Delete a task by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
