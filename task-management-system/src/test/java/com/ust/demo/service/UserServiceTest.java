package com.ust.demo.service;

import com.ust.demo.entity.User;
import com.ust.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Mock the UserRepository

    @InjectMocks
    private UserService userService;  // Inject the mocked repository into the service

    private User testUser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        // Create test user using Lombok builder pattern
        testUser = User.builder()
                .id(1L)
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .role("MEMBER")
                .build();
    }

    @Test
    public void testGetUserById() {
        // Mock repository to return the testUser when findById is called
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(testUser));

        // Call service method and assert results
        User user = userService.getUserById(1L);

        assertNotNull(user);  // Ensure the user is not null
        assertEquals("testuser", user.getUsername());  // Verify username is correct
    }

    @Test
    public void testGetUserByIdNotFound() {
        // Mock repository to return an empty Optional (no user found)
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Assert that RuntimeException is thrown when no user is found
        assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void testGetUserByUsername() {
        // Mock repository to return the testUser when findByUsername is called
        when(userRepository.findByUsername("testuser")).thenReturn(java.util.Optional.of(testUser));

        // Call service method and assert results
        User user = userService.getUserByUsername("testuser");

        assertNotNull(user);  // Ensure the user is not null
        assertEquals("testuser", user.getUsername());  // Verify the username is correct
    }
}
