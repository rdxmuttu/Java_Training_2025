package com.ust.demo.mapper;


import com.ust.demo.dto.user.UserCreateDTO;
import com.ust.demo.dto.user.UserDTO;
import com.ust.demo.dto.user.UserUpdateDTO;
import com.ust.demo.entity.User;
import org.mapstruct.*;

@Mapper(config = MapperConfiguration.class)
public interface UserMapper {

    UserDTO toDTO(User user); // Maps User entity to UserDTO
    User toEntity(UserCreateDTO dto);  // Maps UserCreateDTO to User entity

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        // Only updates non-null fields
    void updateUserFromDTO(UserUpdateDTO dto, @MappingTarget User user);
}
