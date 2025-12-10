package com.ust.demo.repository;

import com.ust.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@ActiveProfiles("test")
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;  // Inject UserRepository for testing

    private User testUser;

    @BeforeEach
    public void setUp() {
        // Prepare test data before each test
        testUser = User.builder()
                .username("testuser")
                .email("testuser@example.com")
                .password("password")
                .role("MEMBER")
                .build();
        testUser = userRepository.save(testUser);  // Save the test user to the repository
    }

    @Test
    public void testFindByUsername() {
        // Test the custom query method for finding a user by username
        User foundUser = userRepository.findByUsername("testuser").orElseThrow(() -> new RuntimeException("User not found"));

        // Verify that the user is correctly retrieved
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }
}
