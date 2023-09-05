package com.imshakthi.javatestserver.kafka.producer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleProducer {

    private final KafkaTemplate<String, String> kafkaSampleTemplate;

    @Autowired
    public SampleProducer(KafkaTemplate<String, String> kafkaSampleTemplate) {
        this.kafkaSampleTemplate = kafkaSampleTemplate;
    }

    public void sendMessage(String msg) {
        kafkaSampleTemplate.send("sample-topic-001", msg);
    }
}
