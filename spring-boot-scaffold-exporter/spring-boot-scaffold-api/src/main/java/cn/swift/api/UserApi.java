package cn.swift.api;

import cn.swift.api.dto.user.UserDTO;
import cn.swift.common.response.BaseResponse;

public interface UserApi {

    BaseResponse<Void> create(UserDTO userDTO);
    
    BaseResponse<UserDTO> query(String username);
    
    BaseResponse<Void> update(UserDTO userDTO);
    
    BaseResponse<Void> delete(UserDTO userDTO);
    
}
