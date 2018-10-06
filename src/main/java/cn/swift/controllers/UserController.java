package cn.swift.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.swift.common.enums.ResponseCode;
import cn.swift.common.response.BaseResponse;
import cn.swift.controllers.request.UserRequest;
import cn.swift.service.UserService;
import cn.swift.service.kafka.KafkaProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "User")
@RestController
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private KafkaProducerService kafkaProducerService;
  
  @ApiOperation(value = "User login")
  @RequestMapping(value = "/user/login", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<String> login(@RequestBody @Valid UserRequest ur) {
    boolean success = userService.login(ur);
    return success ? BaseResponse.success("success") : BaseResponse.fail(ResponseCode.UN_AUTHENTICATION);
  }

  @ApiOperation(value = "Add user")
  @RequestMapping(value = "/user", method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<String> addUser(@RequestBody @Valid UserRequest ur) {
    kafkaProducerService.send(ur);
    return BaseResponse.success(null);
  }
}
