package com.ust.demo.service;

import com.ust.demo.entity.User;
import com.ust.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// @Service: Marks the class as a Spring service
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;  // Injecting UserRepository

    // Method to get a user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Method to find a user by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username " + username));
    }
}
