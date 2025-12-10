package com.ust.demo.dto.task;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TaskUpdateDTO {

    private String title;
    private String description;
    private String status;
    private String priority;
    private Long assigneeId;
    private java.time.LocalDateTime dueDate;
}
