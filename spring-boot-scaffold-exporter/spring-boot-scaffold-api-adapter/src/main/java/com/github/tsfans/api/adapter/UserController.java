package com.github.tsfans.api.adapter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.tsfans.api.UserApi;
import com.github.tsfans.api.dto.user.UserDTO;
import com.github.tsfans.application.UserApplication;
import com.github.tsfans.common.enums.ResponseCode;
import com.github.tsfans.common.response.BaseResponse;
import com.github.tsfans.domain.user.User;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController implements UserApi {
    
    @Autowired
    private UserApplication userApplication;

    @Override
    @PutMapping("")
    public BaseResponse<Void> create(@RequestBody @Valid UserDTO userDTO) {
        userApplication.create(userDTO.getUsername(), userDTO.getPassword());
        return BaseResponse.success(null);
    }

    @Override
    @GetMapping("")
    public BaseResponse<UserDTO> query(@RequestParam("username") String username) {
        User user = userApplication.query(username);
        if(user == null) {
            return BaseResponse.fail(ResponseCode.NOT_FOUND);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        return BaseResponse.success(userDTO);
    }

    @Override
    @PatchMapping("")
    public BaseResponse<Void> update(@RequestBody @Valid UserDTO userDTO) {
        userApplication.update(userDTO.getUsername(), userDTO.getPassword());
        return BaseResponse.success(null);
    }

    @Override
    @DeleteMapping("")
    public BaseResponse<Void> delete(@RequestBody @Valid UserDTO userDTO) {
        userApplication.delete(userDTO.getUsername());
        return BaseResponse.success(null);
    }

}
