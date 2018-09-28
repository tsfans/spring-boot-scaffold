package cn.swift.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.swift.common.enums.ResponseCode;
import cn.swift.common.response.BaseResponse;
import cn.swift.controllers.request.UserRequest;
import cn.swift.model.document.UserDocument;
import cn.swift.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "User")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;
  
  @ApiOperation(value = "user login")
  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<String> login(@RequestBody @Valid UserRequest ur) {
    boolean success = userService.login(ur);
    return success ? BaseResponse.success("success") : BaseResponse.fail(ResponseCode.UN_AUTHENTICATION);
  }

  @ApiOperation(value = "search user by username")
  @GetMapping(value = "/search/{username}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<List<UserDocument>> searchUsername(@PathVariable String username) {
    return BaseResponse.success(userService.searchByUsername(username));
  }

  @ApiOperation(value = "search user by user id")
  @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<UserDocument> searchByUserId(@PathVariable String id) {
    return BaseResponse.success(userService.searchByUserId(id));
  }
}
