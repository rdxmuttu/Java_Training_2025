package com.ust.demo.mapper;

import com.ust.demo.dto.project.ProjectCreateDTO;
import com.ust.demo.dto.project.ProjectDTO;
import com.ust.demo.dto.project.ProjectUpdateDTO;
import com.ust.demo.entity.Project;
import org.mapstruct.*;

@Mapper(config = MapperConfiguration.class)
public interface ProjectMapper {

    @Mapping(source = "owner.id", target = "ownerId")  // Maps the owner’s ID to ownerId in DTO
    ProjectDTO toDTO(Project project);  // Maps Project entity to ProjectDTO

    Project toEntity(ProjectCreateDTO dto);  // Maps ProjectCreateDTO to Project entity

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        // Ignores null fields from DTO when updating the Project entity
    void updateProjectFromDTO(ProjectUpdateDTO dto, @MappingTarget Project project);
}
