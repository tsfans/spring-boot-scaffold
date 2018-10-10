package cn.swift.controllers;

import cn.swift.common.response.BaseResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PingController {

  @GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<String> ping() {
    return BaseResponse.success("pong");
  }

}
