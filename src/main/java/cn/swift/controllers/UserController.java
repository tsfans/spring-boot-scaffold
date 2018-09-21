package cn.swift.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.swift.common.enums.ResponseCode;
import cn.swift.common.response.BaseResponse;
import cn.swift.controllers.request.UserRequest;
import cn.swift.service.UserService;

@RestController
public class UserController {

  @Autowired
  private UserService userService;
  
  @RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
  public BaseResponse<String> login(@RequestBody @Valid UserRequest ur) {
    boolean success = userService.login(ur);
    return success ? BaseResponse.success("success") : BaseResponse.fail(ResponseCode.UN_AUTHENTICATION);
  }
}
