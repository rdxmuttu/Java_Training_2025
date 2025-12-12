package com.ust.demo.service;

import com.ust.demo.entity.Task;
import com.ust.demo.exception.ResourceNotFoundException;
import com.ust.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Service: Marks the class as a Spring service
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;  // Injecting TaskRepository

    // Method to get a task by ID
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));  // Throws exception if task is not found
    }

    // Method to get tasks by assignee ID
    public List<Task> getTasksByAssignee(Long assigneeId) {
        return taskRepository.findByAssigneeId(assigneeId);  // Fetches tasks assigned to the given user ID
    }

    // Method to create a new task
    public Task createTask(Task task) {
        return taskRepository.save(task);  // Saves and returns the created task
    }

    // Method to update an existing task
    public Task updateTask(Long id, Task updatedTask) {
        Task task = getTaskById(id);  // Gets the existing task
        task.setTitle(updatedTask.getTitle());  // Updates task fields
        task.setDescription(updatedTask.getDescription());
        task.setPriority(updatedTask.getPriority());
        task.setStatus(updatedTask.getStatus());
        return taskRepository.save(task);  // Saves the updated task
    }

    // Method to delete a task
    public void deleteTask(Long id) {
        Task task = getTaskById(id);  // Retrieves the task by ID
        taskRepository.delete(task);  // Deletes the task
    }
    @Transactional
    public void updateTaskStatuses(List<Long> taskIds, String newStatus) {
        int batchSize = 50;  // Batch size for update
        for (int i = 0; i < taskIds.size(); i++) {
            taskRepository.updateStatus(taskIds.get(i), newStatus);  // Perform batch update
            if (i % batchSize == 0) {  // Commit in batches
                taskRepository.flush();
                taskRepository.clear();
            }
        }
    }
}
