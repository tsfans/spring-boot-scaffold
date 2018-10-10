package cn.swift.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaProducerService {

  @Value("${spring.kafka.topic.userTopic}")
  private String topic;
  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  public void send(Object message) {
    ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);
    future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
      @Override
      public void onSuccess(SendResult<String, Object> result) {
        log.info("Successfully publish to kafka with offset: {} and message: {}",
            result.getRecordMetadata().offset(), message);
      }

      @Override
      public void onFailure(Throwable ex) {
        log.error("Unable to publish to kafka with message: {}, errorMessage: {}", message,
            ex.getMessage());
      }
    });
  }
}
