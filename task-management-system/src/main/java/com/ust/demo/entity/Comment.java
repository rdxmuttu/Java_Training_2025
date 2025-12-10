package com.ust.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


@Entity  // JPA entity definition
@Table(
        name = "comments",
        indexes = {
                @Index(name = "idx_task_id", columnList = "task_id")
        }
)@Getter @Setter  // Automatically generate getters and setters
@NoArgsConstructor @AllArgsConstructor  // Default and parameterized constructors
@Builder  // Builder pattern for easy creation of Comment objects
public class Comment {

    @Id  // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generated value
    private Long id;

    @NotBlank(message = "Comment cannot be empty")  // Validation to ensure comment is not blank
    @Column(nullable = false)  // Marks the comment as a non-null field
    private String content;

    // ---------------------------- TASK ---------------------------- //
    @ManyToOne(fetch = FetchType.LAZY)  // Many comments can belong to one task
    @JoinColumn(name = "task_id", nullable = false)  // Foreign key for the task
    @BatchSize(size = 20)
    private Task task;

    // ---------------------------- USER ---------------------------- //
    @ManyToOne(fetch = FetchType.LAZY)  // Many comments can be written by one user
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key for user
    private User user;

    @CreationTimestamp  // Auto-generated timestamp for when the comment was created
    @Column(updatable = false)  // Prevent updates to this field
    private LocalDateTime createdAt;
}
