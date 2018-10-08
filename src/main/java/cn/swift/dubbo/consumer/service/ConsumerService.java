package cn.swift.dubbo.consumer.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;
import cn.swift.dubbo.provider.service.HelloService;

@Service
@Profile("consumer")
public class ConsumerService {

  @Reference(version = "${dubbo.service.version}", application = "${dubbo.application.id}",
      url = "dubbo://localhost:3864")
  private HelloService helloService;

  public String sayHello(String name) {
    return helloService.sayHello(name);
  }
}
