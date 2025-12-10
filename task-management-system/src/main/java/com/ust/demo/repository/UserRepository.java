package com.ust.demo.repository;

import com.ust.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository: Indicates this interface is a Spring Data repository
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query method to find a user by their username
    Optional<User> findByUsername(String username);  // Finds user by username
}
