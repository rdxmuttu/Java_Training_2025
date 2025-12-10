package com.ust.demo.dto.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder // Lombok Builder pattern for creating UserDTO objects
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String role; // Role of the user (ADMIN, OWNER, MEMBER)
}
