package com.ust.demo.repository;

import com.ust.demo.entity.Task;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository: Marks this interface as a Spring Data repository
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Custom query methods to retrieve tasks by assignee ID, project ID, or status
    List<Task> findByAssigneeId(Long assigneeId);  // Find tasks assigned to a user
   // List<Task> findByProjectId(Long projectId);    // Find tasks in a specific project
   // List<Task> findByStatus(String status);        // Find tasks by status
// Using @EntityGraph to fetch the 'assignee' and 'project' associations eagerly
    @EntityGraph(attributePaths = {"assignee", "project"})
    // Pagination and sorting by title
    Page<Task> findByProjectId(Long projectId, Pageable pageable);

    // Sorting by status
    List<Task> findByStatus(String status, Sort sort);

    // Optimized query using join fetch
    @Query("SELECT t FROM Task t JOIN FETCH t.assignee WHERE t.project.id = :projectId")
    List<Task> findByProjectIdWithAssignee(@Param("projectId") Long projectId);

//    @Cacheable(value = "tasks", key = "#id")
//    Task findById(Long id);

    void updateStatus(Long aLong, String newStatus);

    void clear();
}
