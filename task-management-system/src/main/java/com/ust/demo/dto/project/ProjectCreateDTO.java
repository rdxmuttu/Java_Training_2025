package com.ust.demo.dto.project;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProjectCreateDTO {

    @NotBlank(message = "Project name is required")
    private String name; // The name of the project
    private String description; // Optional description of the project
}
