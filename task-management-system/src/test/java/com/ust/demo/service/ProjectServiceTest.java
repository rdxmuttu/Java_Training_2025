package com.ust.demo.service;

import com.ust.demo.entity.Project;
import com.ust.demo.exception.ResourceNotFoundException;
import com.ust.demo.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;  // Mocking the ProjectRepository

    @InjectMocks
    private ProjectService projectService;  // Inject the mocked repository into the service

    private Project testProject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initializes mocks
        // Using Lombok's builder pattern to initialize the testProject object
        testProject = Project.builder()
                .id(1L)
                .name("Test Project")
                .description("Test Description")
                .build();
    }

    @Test
    public void testGetProjectById() {
        when(projectRepository.findById(1L)).thenReturn(java.util.Optional.of(testProject));  // Mock repository call

        Project project = projectService.getProjectById(1L);

        assertNotNull(project);  // Ensure the project is not null
        assertEquals("Test Project", project.getName());  // Verify the project name
    }

    @Test
    public void testGetProjectByIdNotFound() {
        when(projectRepository.findById(1L)).thenReturn(java.util.Optional.empty());  // Mock empty result

        assertThrows(ResourceNotFoundException.class, () -> projectService.getProjectById(1L));  // Expect exception
    }
}
