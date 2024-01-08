package com.github.tsfans.api.dto.user;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    @NotBlank(message = "username can not be blank")
    private String username;
    
    @NotBlank(message = "password can not be blank")
    private String password;
    
}
