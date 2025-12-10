package com.ust.demo.mapper;

import com.ust.demo.dto.task.TaskCreateDTO;
import com.ust.demo.dto.task.TaskDTO;
import com.ust.demo.dto.task.TaskUpdateDTO;
import com.ust.demo.entity.Task;
import org.mapstruct.*;

@Mapper(config = MapperConfiguration.class)
public interface TaskMapper {

    @Mapping(source = "assignee.id", target = "assigneeId")
    @Mapping(source = "project.id", target = "projectId")
    TaskDTO toDTO(Task task);  // Maps Task entity to TaskDTO

    Task toEntity(TaskCreateDTO dto);  // Maps TaskCreateDTO to Task entity

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        // Updates only non-null properties in the existing Task entity
    void updateTaskFromDTO(TaskUpdateDTO dto, @MappingTarget Task task);
}
