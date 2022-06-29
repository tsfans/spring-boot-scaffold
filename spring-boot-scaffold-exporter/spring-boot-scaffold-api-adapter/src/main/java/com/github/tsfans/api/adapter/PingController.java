package com.github.tsfans.api.adapter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tsfans.api.PingApi;
import com.github.tsfans.common.response.BaseResponse;

@RestController
public class PingController implements PingApi {

    @Override
    @GetMapping("/ping")
    public BaseResponse<String> ping() {
        return BaseResponse.success("pong");
    }

}
