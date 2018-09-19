package cn.swift.controllers;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.swift.common.response.BaseResponse;
import cn.swift.controllers.request.UserRequest;

@RestController
public class UserController {

  @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
  public BaseResponse<String> login(@RequestBody @Valid UserRequest ur) {

    return BaseResponse.success("success");
  }
}
