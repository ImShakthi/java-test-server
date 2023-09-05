package com.imshakthi.javatestserver.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SampleConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(SampleConsumer.class);

    @KafkaListener(topics = "sample-topic-001" , groupId="test")
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
