package cn.swift.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import cn.swift.controllers.request.UserRequest;
import cn.swift.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaConsumerService {

  @Autowired
  private UserService userService;

  @SuppressWarnings("unchecked")
  @KafkaListener(topics = {"${spring.kafka.topic.userTopic}"})
  public void consume(Object o) {
    ConsumerRecord<String, UserRequest> cr = (ConsumerRecord<String, UserRequest>) o;
    UserRequest ur = cr.value();
    log.info("Received from kafka with data: {}", ur);
    userService.addUser(ur);
  }
}
