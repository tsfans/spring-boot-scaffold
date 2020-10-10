package cn.swift.api.adapter;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.swift.api.UserApi;
import cn.swift.api.dto.user.UserDTO;
import cn.swift.application.UserApplication;
import cn.swift.common.response.BaseResponse;
import cn.swift.domain.user.User;

@RestController
@RequestMapping("/user")
public class UserController implements UserApi {
    
    private UserApplication userApplication;

    @Override
    @PutMapping("")
    public BaseResponse<Void> create(@RequestBody @Valid UserDTO userDTO) {
        userApplication.create(userDTO.getUsername(), userDTO.getPassword());
        return BaseResponse.success(null);
    }

    @Override
    @GetMapping("")
    public BaseResponse<UserDTO> query(String username) {
        User user = userApplication.query(username);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return BaseResponse.success(userDTO);
    }

    @Override
    @PatchMapping("")
    public BaseResponse<Void> update(UserDTO userDTO) {
        userApplication.update(userDTO.getUsername(), userDTO.getPassword());
        return BaseResponse.success(null);
    }

    @Override
    @DeleteMapping("")
    public BaseResponse<Void> delete(UserDTO userDTO) {
        userApplication.delete(userDTO.getUsername());
        return BaseResponse.success(null);
    }

}
