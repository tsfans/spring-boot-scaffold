package cn.swift.service.kafka.serializer;

import org.springframework.kafka.support.serializer.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KafkaJsonDeserializer<T> extends JsonDeserializer<T> {


  @Override
  public T deserialize(String topic, byte[] data) {
    T t = null;
    try {
      t = super.deserialize(topic, data);
    } catch (Exception e) {
      log.error("Error when deserializing byte[] due to: {}", e.getMessage());
    }
    return t;
  }


}
