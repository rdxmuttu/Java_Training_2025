package com.ust.demo.dto.project;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProjectUpdateDTO {

    private String name; // The updated name for the project
    private String description;
}
