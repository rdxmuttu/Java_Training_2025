package com.ust.demo.dto.user;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserUpdateDTO {

    private String username;// The username field to update
    private String email;
}
