package cn.swift.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.swift.common.response.BaseResponse;


@RestController
public class PingController {
  
  @RequestMapping(value = "/ping", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<String> ping() {
    return BaseResponse.success("pong");
  }
  
}
