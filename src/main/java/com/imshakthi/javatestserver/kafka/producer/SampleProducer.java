package com.imshakthi.javatestserver.kafka.producer;


import com.imshakthi.javatestserver.kafka.config.KafkaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleProducer {

    private final KafkaTemplate<String, String> kafkaSampleTemplate;

    private final KafkaConfig kafkaConfig;

    @Autowired
    public SampleProducer(KafkaTemplate<String, String> kafkaSampleTemplate, KafkaConfig kafkaConfig) {
        this.kafkaSampleTemplate = kafkaSampleTemplate;
        this.kafkaConfig = kafkaConfig;
    }

    public void sendMessage(String msg) {
        kafkaSampleTemplate.send(kafkaConfig.getMessageWriterTopicName(), msg);
    }
}
