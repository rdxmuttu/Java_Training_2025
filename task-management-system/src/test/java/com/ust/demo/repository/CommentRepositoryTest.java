package com.ust.demo.repository;

import com.ust.demo.entity.Comment;
import com.ust.demo.entity.Task;
import com.ust.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;  // Inject the CommentRepository for testing

    @Autowired
    private TaskRepository taskRepository;  // Inject TaskRepository for creating test tasks

    @Autowired
    private UserRepository userRepository;  // Inject UserRepository for creating test users

    private User testUser;
    private Task testTask;

    @BeforeEach
    public void setUp() {
        // Prepare the test data before each test

        // Create test user
        testUser = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .role("MEMBER")
                .build();
        testUser = userRepository.save(testUser);  // Save the user

        // Create a test task for the test user
        testTask = Task.builder()
                .title("Test Task")
                .description("Test Task Description")
                .status("PENDING")
                .priority("MEDIUM")
                .assignee(testUser)
                .build();
        testTask = taskRepository.save(testTask);  // Save the task
    }

    @Test
    public void testFindByTaskId() {
        // Create a comment for the test task
        Comment comment = Comment.builder()
                .content("Test Comment")
                .task(testTask)
                .user(testUser)
                .build();
        commentRepository.save(comment);  // Save the comment

        // Retrieve comments associated with the test task
        List<Comment> comments = commentRepository.findByTaskId(testTask.getId());

        // Verify that the comment is correctly retrieved
        assertFalse(comments.isEmpty());
        assertEquals(1, comments.size());
        assertEquals("Test Comment", comments.get(0).getContent());
    }
}
