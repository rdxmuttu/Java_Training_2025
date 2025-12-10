package com.ust.demo.mapper;

import com.ust.demo.dto.comment.CommentCreateDTO;
import com.ust.demo.dto.comment.CommentDTO;
import com.ust.demo.entity.Comment;
import org.mapstruct.*;

@Mapper(config = MapperConfiguration.class)
public interface CommentMapper {

    @Mapping(source = "task.id", target = "taskId")  // Maps task ID in the Comment entity
    @Mapping(source = "user.id", target = "userId")  // Maps user ID in the Comment entity
    CommentDTO toDTO(Comment comment);  // Maps Comment entity to CommentDTO

    Comment toEntity(CommentCreateDTO dto);  // Maps CommentCreateDTO to Comment entity
}
