package com.ust.demo.dto.project;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProjectDTO {

    private Long id;
    private String name;
    private String description;

    private Long ownerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
