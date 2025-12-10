package com.ust.demo.dto.task;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TaskDTO {

    private Long id;
    private String title;
    private String description;

    private String status;        // Task status (e.g., PENDING, IN_PROGRESS)
    private String priority;      // Task priority (e.g., LOW, MEDIUM, HIGH)

    private Long assigneeId;      // ID of the user assigned to the task
    private Long projectId;       // ID of the project to which the task belongs

    private LocalDateTime dueDate;  // Due date of the task
    private LocalDateTime createdAt;  // The creation timestamp of the task
    private LocalDateTime updatedAt;  // The last update timestamp of the task
}
