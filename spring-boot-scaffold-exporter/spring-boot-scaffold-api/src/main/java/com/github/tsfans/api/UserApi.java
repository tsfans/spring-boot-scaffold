package com.github.tsfans.api;

import com.github.tsfans.api.dto.user.UserDTO;
import com.github.tsfans.common.response.BaseResponse;

public interface UserApi {

    BaseResponse<Void> create(UserDTO userDTO);
    
    BaseResponse<UserDTO> query(String username);
    
    BaseResponse<Void> update(UserDTO userDTO);
    
    BaseResponse<Void> delete(UserDTO userDTO);
    
}
