package cn.swift.api.dto.user;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {

    @NotBlank(message = "username can not be blank")
    private String username;
    
    private String password;
    
}
