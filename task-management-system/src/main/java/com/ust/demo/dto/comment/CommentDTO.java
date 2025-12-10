package com.ust.demo.dto.comment;

import lombok.*;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CommentDTO {

    private Long id;          // Comment ID
    private String content;   // Content of the comment

    private Long taskId;      // ID of the task associated with the comment
    private Long userId;      // ID of the user who made the comment

    private LocalDateTime createdAt;  // Timestamp for when the comment was created
}
