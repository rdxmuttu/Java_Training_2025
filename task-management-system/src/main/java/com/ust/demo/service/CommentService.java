package com.ust.demo.service;

import com.ust.demo.entity.Comment;
import com.ust.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service: Marks the class as a Spring service
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;  // Injecting CommentRepository

    // Method to get comments by task ID
    public List<Comment> getCommentsByTaskId(Long taskId) {
        return commentRepository.findByTaskId(taskId);  // Retrieves all comments for a given task
    }

    // Method to create a new comment
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);  // Saves and returns the new comment
    }
}
