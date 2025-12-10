package com.ust.demo.dto.comment;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CommentCreateDTO {

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private Long taskId;
    private Long userId;
}
