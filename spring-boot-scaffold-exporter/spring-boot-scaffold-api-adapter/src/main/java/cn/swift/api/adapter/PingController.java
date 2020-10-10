package cn.swift.api.adapter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.swift.api.PingApi;
import cn.swift.common.response.BaseResponse;

@RestController
public class PingController implements PingApi {

    @Override
    @GetMapping("/ping")
    public BaseResponse<String> ping() {
        return BaseResponse.success("pong");
    }

}
