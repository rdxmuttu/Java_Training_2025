package com.ust.demo.dto.user;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserCreateDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email")
    @NotBlank
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
