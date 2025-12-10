package com.ust.demo.repository;

import com.ust.demo.entity.Project;
import com.ust.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
// @SpringBootTest: This annotation is used to load the full application context for integration testing
@SpringBootTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;  // Inject the repository to test

    @Autowired
    private UserRepository userRepository;  // Inject the UserRepository for creating test data

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Prepare the test data before each test

        // Use Lombok's builder pattern to create a User object
        testUser = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .role("MEMBER")
                .build();

        // Save the test user to the repository (database)
        testUser = userRepository.save(testUser);  // Save and return the user object
    }

    @Test
    public void testFindByOwnerId() {
        // Create a project assigned to the test user using Lombok builder
        Project project = Project.builder()
                .name("Test Project")
                .description("Test Description")
                .owner(testUser)  // Assign the test user as the owner
                .build();

        // Save the project to the repository (database)
        projectRepository.save(project);

        // Find projects by the ownerId (testUser's ID)
        List<Project> projects = projectRepository.findByOwnerId(testUser.getId());

        // Verify that the project is correctly retrieved
        assertFalse(projects.isEmpty());
        assertEquals(1, projects.size());
        assertEquals("Test Project", projects.get(0).getName());
    }
}
