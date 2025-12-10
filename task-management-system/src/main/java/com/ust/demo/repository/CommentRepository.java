package com.ust.demo.repository;

import com.ust.demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Repository: Marks the interface as a Spring Data repository
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Custom query method to retrieve comments for a specific task
    List<Comment> findByTaskId(Long taskId);  // Finds all comments for a specific task
}
