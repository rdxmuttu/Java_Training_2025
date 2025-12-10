package com.ust.demo.repository;

import com.ust.demo.entity.Project;
import com.ust.demo.entity.Task;
import com.ust.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;  // Inject the TaskRepository for testing

    @Autowired
    private UserRepository userRepository;  // Inject UserRepository for creating test users

    @Autowired
    private ProjectRepository projectRepository;  // Inject ProjectRepository for creating test projects

    private User testUser;
    private Project testProject;

    @BeforeEach
    public void setUp() {
        // Create a test user
        testUser = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .role("MEMBER")
                .build();
        testUser = userRepository.save(testUser);  // Save the test user

        // Create a test project for the test user
        testProject = Project.builder()
                .name("Test Project")
                .description("Test Project Description")
                .owner(testUser)
                .build();
        testProject = projectRepository.save(testProject);  // Save the project
    }

    @Test
    public void testFindByAssigneeId() {
        // Create a task and assign it to the test user
        Task task = Task.builder()
                .title("Test Task")
                .description("Test Task Description")
                .status("PENDING")
                .priority("MEDIUM")
                .assignee(testUser)
                .project(testProject)
                .build();
        taskRepository.save(task);  // Save the task

        // Retrieve tasks assigned to the test user
        List<Task> tasks = taskRepository.findByAssigneeId(testUser.getId());

        // Verify that the task is correctly retrieved
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
    }
}

