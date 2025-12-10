package com.ust.demo.dto.task;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TaskCreateDTO {

    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    @NotBlank(message = "Status is required")
    private String status;  // The status of the task (PENDING, IN_PROGRESS)

    @NotBlank(message = "Priority is required")
    private String priority;  // The priority of the task (LOW, MEDIUM, HIGH)

    private Long assigneeId;
    private Long projectId;

    private java.time.LocalDateTime dueDate;
}
