package cn.swift.dubbo.provider.service.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import cn.swift.dubbo.provider.service.HelloService;

@Profile("!consumer")
@Service(version = "${dubbo.service.version}", application = "${dubbo.application.id}",
    protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}",
    interfaceClass = HelloService.class)
@Component
public class HelloServiceImpl implements HelloService {

  @Override
  public String sayHello(String name) {
    return "Hello" + name + " --- From Spring Boot";
  }

}
