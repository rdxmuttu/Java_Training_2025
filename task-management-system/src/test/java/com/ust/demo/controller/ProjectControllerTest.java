package com.ust.demo.controller;

import com.ust.demo.entity.Project;
import com.ust.demo.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ProjectControllerTest {

    private MockMvc mockMvc;  // MockMvc instance to test the controller

    @Mock
    private ProjectService projectService;  // Mock the service

    @InjectMocks
    private ProjectController projectController;  // Inject mocked service into controller

    private Project testProject;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        // Using Lombok's builder pattern to initialize the test project object
        testProject = Project.builder()
                .id(1L)
                .name("Test Project")
                .description("Test Description")
                .build();

        // Manually initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void testGetProjectById() throws Exception {
        // Mock the service method to return the test project
        when(projectService.getProjectById(1L)).thenReturn(testProject);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/api/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Project"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }
}
