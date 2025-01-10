package com.imshakthi.javatestserver.kafka.consumer;

import static com.imshakthi.javatestserver.constant.Messaging.GROUP_ID_SAMPLE_001;
import static com.imshakthi.javatestserver.constant.Messaging.TOPIC_NAME_SAMPLE_001;

import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SampleConsumer {
  private static final Logger LOGGER = LoggerFactory.getLogger(SampleConsumer.class);

  @KafkaListener(
      topics = TOPIC_NAME_SAMPLE_001,
      groupId = GROUP_ID_SAMPLE_001,
      autoStartup = "${spring.kafka.enabled:false}")
  public void listenGroupFoo(String message) {
    try {
      LOGGER.info("timer started...");
      TimeUnit.SECONDS.sleep(5);
      LOGGER.info("timer ended...");
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    LOGGER.info("Received Message is {}: ", message);
  }
}
