package com.ust.demo.service;

import com.ust.demo.entity.Task;
import com.ust.demo.exception.ResourceNotFoundException;
import com.ust.demo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;  // Mock the TaskRepository

    @InjectMocks
    private TaskService taskService;  // Inject the mocked repository into the service

    private Task testTask;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        // Using Lombok builder to initialize the test task object
        testTask = Task.builder()
                .id(1L)
                .title("Test Task")
                .description("Test Task Description")
                .status("PENDING")
                .priority("MEDIUM")
                .build();
    }

    @Test
    public void testGetTaskById() {
        // Mock the repository to return testTask when findById is called
        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.of(testTask));

        // Call service method and assert results
        Task task = taskService.getTaskById(1L);

        assertNotNull(task);  // Assert that the task is not null
        assertEquals("Test Task", task.getTitle());  // Assert the title matches
    }

    @Test
    public void testGetTaskByIdNotFound() {
        // Mock repository call to return an empty Optional (no task found)
        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Assert that ResourceNotFoundException is thrown when no task is found
        assertThrows(ResourceNotFoundException.class, () -> taskService.getTaskById(1L));
    }
}
