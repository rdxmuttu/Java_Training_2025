package com.ust.demo.controller;

import com.ust.demo.entity.Project;
import com.ust.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")  // Base URL for all project-related requests
public class ProjectController {

    @Autowired
    private ProjectService projectService;  // Injecting the ProjectService

    // Get all projects for a specific user (by owner ID)
    @GetMapping
    public ResponseEntity<List<Project>> getProjectsByOwner(@RequestParam Long ownerId) {
        List<Project> projects = projectService.getProjectsByOwner(ownerId);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // Get a specific project by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    // Create a new project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // Update an existing project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        Project project = projectService.updateProject(id, updatedProject);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    // Delete a project by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
