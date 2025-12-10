package com.ust.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@Entity  // JPA Entity annotation
@Table(
        name = "tasks",
        indexes = {
                @Index(name = "idx_assignee_id", columnList = "assignee_id"),
                @Index(name = "idx_project_id", columnList = "project_id"),
                @Index(name = "idx_status", columnList = "status")
        }
)  // Defines indexes on status and priority for performance improvement
@Getter @Setter  // Lombok annotations for automatic getter and setter generation
@NoArgsConstructor @AllArgsConstructor  // Default and parameterized constructors
@Builder  // Builder pattern for easy creation of Task objects
public class Task {

    @Id  // Marks the primary key field
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment ID
    private Long id;

    @NotBlank(message = "Task title cannot be empty")  // Validates that task title is not empty
    @Column(nullable = false)  // Non-null constraint for title
    private String title;

    private String description;

    @Column(nullable = false)  // Ensures status is not null
    private String status;  // Task status like PENDING, IN_PROGRESS, COMPLETED, CANCELLED

    @Column(nullable = false)  // Ensures priority is not null
    private String priority;  // Priority of the task (LOW, MEDIUM, HIGH, URGENT)

    // ---------------------------- ASSIGNEE ---------------------------- //

    @ManyToOne(fetch = FetchType.LAZY)  // Many tasks can have one assignee
    @JoinColumn(name = "assignee_id")  // Foreign key for assignee
    @BatchSize(size = 20) // Hibernate will load 20 tasks' assignee in a single batch instead of 1 per row
    private User assignee;

    // ---------------------------- PROJECT ---------------------------- //

    @ManyToOne(fetch = FetchType.LAZY)  // Many tasks can belong to one project
    @JoinColumn(name = "project_id", nullable = false)  // Foreign key for project
    @BatchSize(size = 20)
    private Project project;

    private LocalDateTime dueDate;

    @CreationTimestamp  // Timestamp on task creation
    @Column(updatable = false)  // Prevents update of this field
    private LocalDateTime createdAt;

    @UpdateTimestamp  // Updates this field whenever the task is updated
    private LocalDateTime updatedAt;

    // ---------------------------- COMMENTS ---------------------------- //

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)  // One task can have many comments
    private List<Comment> comments;
}
