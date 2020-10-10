package cn.swift.api;

import cn.swift.common.response.BaseResponse;

public interface PingApi {

    BaseResponse<String> ping();
    
}
