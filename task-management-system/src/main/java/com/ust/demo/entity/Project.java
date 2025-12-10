package com.ust.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity  // Marks this class as a JPA entity
@Table(
        name = "projects",
        indexes = {
                @Index(name = "idx_owner_id", columnList = "owner_id") // improves foreign key lookups
        }
)
@Getter @Setter  // Lombok annotations for getter/setter generation
@NoArgsConstructor @AllArgsConstructor  // Default and parameterized constructors
@Builder  // Lombok builder pattern for easy object creation

@NamedEntityGraph(
        name = "Project.withOwner",
        attributeNodes = {
                @NamedAttributeNode("owner")     // tells JPA to preload the owner data in one SQL join
        }
)public class Project {

    @Id  // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-incrementing ID
    private Long id;

    @NotBlank(message = "Project name cannot be empty")  // Validates that project name cannot be blank
    @Column(nullable = false)  // Ensures the project name is not null
    private String name;

    private String description;

    // ---------------------------- OWNER (User) ---------------------------- //

    @ManyToOne(fetch = FetchType.LAZY)  // Many projects can have one owner
    @JoinColumn(name = "owner_id", nullable = false)  // The owner_id column is the foreign key
    private User owner;

    @CreationTimestamp  // Automatically generated timestamp for creation
    @Column(updatable = false)  // Prevents this field from being updated
    private LocalDateTime createdAt;

    @UpdateTimestamp  // Automatically updates the timestamp on each update
    private LocalDateTime updatedAt;

    // ---------------------------- TASKS ---------------------------- //

    //@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)  // One project contains many tasks
    // When project is deleted => all tasks deleted

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;
}

