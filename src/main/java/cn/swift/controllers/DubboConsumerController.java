package cn.swift.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.swift.common.response.BaseResponse;
import cn.swift.dubbo.consumer.service.ConsumerService;

@RestController
@Profile("consumer")
public class DubboConsumerController {

  @Autowired
  private ConsumerService consumerService;

  @GetMapping(value = "/sayHello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public BaseResponse<String> sayHello(@Param("name") String name) {
    return BaseResponse.success(consumerService.sayHello(name));
  }
}
