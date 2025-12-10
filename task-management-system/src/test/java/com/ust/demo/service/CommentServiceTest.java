package com.ust.demo.service;

import com.ust.demo.entity.Comment;
import com.ust.demo.entity.Task;
import com.ust.demo.entity.User;
import com.ust.demo.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;  // Mock CommentRepository

    @Mock
    private TaskService taskService;  // Mock TaskService for creating tasks

    @Mock
    private UserService userService;  // Mock UserService for creating users

    @InjectMocks
    private CommentService commentService;  // Inject the mocked repositories into the CommentService

    private Comment testComment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        // Create a user and a task using Lombok builder pattern
        User testUser = User.builder()
                .id(1L)
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .role("MEMBER")
                .build();

        Task testTask = Task.builder()
                .id(1L)
                .title("Test Task")
                .description("Test Task Description")
                .status("PENDING")
                .priority("MEDIUM")
                .build();

        testComment = Comment.builder()
                .content("Test Comment")
                .task(testTask)
                .user(testUser)
                .build();
    }

    @Test
    public void testCreateComment() {
        // Mock repository save call
        when(commentRepository.save(testComment)).thenReturn(testComment);

        // Call the service method and assert the result
        Comment createdComment = commentService.createComment(testComment);

        assertNotNull(createdComment);  // Ensure the comment is not null
        assertEquals("Test Comment", createdComment.getContent());  // Check if content matches
    }

    @Test
    public void testGetCommentsByTaskId() {
        // Mock repository to return a list containing the testComment
        when(commentRepository.findByTaskId(1L)).thenReturn(java.util.List.of(testComment));

        // Call the service method and assert the results
        var comments = commentService.getCommentsByTaskId(1L);

        assertFalse(comments.isEmpty());  // Ensure the list is not empty
        assertEquals("Test Comment", comments.get(0).getContent());  // Check comment content
    }
}
