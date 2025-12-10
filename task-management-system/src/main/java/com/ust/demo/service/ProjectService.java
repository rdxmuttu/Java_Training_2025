package com.ust.demo.service;

import com.ust.demo.entity.Project;
import com.ust.demo.exception.ResourceNotFoundException;
import com.ust.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service: This annotation marks the class as a service component in the Spring context
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;  // Injecting ProjectRepository

    // Method to get a project by its ID
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + id));  // Throws exception if project is not found
    }

    // Method to get all projects by owner ID
    public List<Project> getProjectsByOwner(Long ownerId) {
        return projectRepository.findByOwnerId(ownerId);  // Fetches all projects by the owner ID
    }

    // Method to create a new project
    public Project createProject(Project project) {
        return projectRepository.save(project);  // Saves and returns the new project
    }

    // Method to update an existing project
    public Project updateProject(Long id, Project updatedProject) {
        Project project = getProjectById(id);  // Gets the existing project
        project.setName(updatedProject.getName());  // Updates project fields
        project.setDescription(updatedProject.getDescription());
        return projectRepository.save(project);  // Saves the updated project
    }

    // Method to delete a project
    public void deleteProject(Long id) {
        Project project = getProjectById(id);  // Gets the project by ID
        projectRepository.delete(project);  // Deletes the project
    }
}
