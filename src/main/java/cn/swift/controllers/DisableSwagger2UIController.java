package cn.swift.controllers;

import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.swift.common.enums.ResponseCode;
import cn.swift.common.response.BaseResponse;

@Profile("!dev")
@RestController
public class DisableSwagger2UIController {

  @RequestMapping(value = "/swagger-ui.html", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<String> swaggerUi() {
    return BaseResponse.fail(ResponseCode.FAILURE);
  }
}
