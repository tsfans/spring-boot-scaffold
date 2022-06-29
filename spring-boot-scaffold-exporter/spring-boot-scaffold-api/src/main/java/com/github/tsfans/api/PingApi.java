package com.github.tsfans.api;

import com.github.tsfans.common.response.BaseResponse;

public interface PingApi {

    BaseResponse<String> ping();
    
}
