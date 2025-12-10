package com.ust.demo.repository;

import com.ust.demo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository: This annotation marks the interface as a Spring Data repository
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Custom query method to find projects by the owner ID
    List<Project> findByOwnerId(Long ownerId);  // Retrieves projects by owner ID
}
