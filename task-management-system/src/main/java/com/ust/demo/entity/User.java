package com.ust.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@Entity                                     // Marks this class as a JPA entity, meaning it will map to a database table
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_username", columnList = "username"), // indexing username for fast login
                @Index(name = "idx_email", columnList = "email")        // indexing email
        }
)                    // Specifies the table name in the database
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder                                    // Allows building objects using builder pattern
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Auto-increment primary key
    private Long id;

    @NotBlank(message = "Username cannot be empty")
    @Column(nullable = false, unique = true)
    // NOT NULL + UNIQUE constraint
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
    // Roles: ADMIN, OWNER, MEMBER

    @CreationTimestamp
    // Automatically inserts timestamp at record creation
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // ---------------------------- RELATIONSHIPS ---------------------------- //

   // @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    // One user can OWN many projects
   @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
   // LAZY means the list of projects is only fetched when accessed
    private java.util.List<Project> projects;

    @OneToMany(mappedBy = "assignee")
    // User assigned to multiple tasks
    private java.util.List<Task> assignedTasks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // User writes multiple comments
    private java.util.List<Comment> comments;
}


