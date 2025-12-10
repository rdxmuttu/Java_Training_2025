package com.ust.demo.repository;

import com.ust.demo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository: Marks this interface as a Spring Data repository
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Custom query methods to retrieve tasks by assignee ID, project ID, or status
    List<Task> findByAssigneeId(Long assigneeId);  // Find tasks assigned to a user
    List<Task> findByProjectId(Long projectId);    // Find tasks in a specific project
    List<Task> findByStatus(String status);        // Find tasks by status
}
