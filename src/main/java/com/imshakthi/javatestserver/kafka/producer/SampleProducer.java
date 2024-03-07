package com.imshakthi.javatestserver.kafka.producer;


import com.imshakthi.javatestserver.kafka.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class SampleProducer {

    private final static Logger LOGGER = LoggerFactory.getLogger(SampleProducer.class);

    private final KafkaTemplate<String, String> kafkaSampleTemplate;

    private final KafkaConfig kafkaConfig;

    @Autowired
    public SampleProducer(KafkaTemplate<String, String> kafkaSampleTemplate, KafkaConfig kafkaConfig) {
        this.kafkaSampleTemplate = kafkaSampleTemplate;
        this.kafkaConfig = kafkaConfig;
    }

    public void sendMessage(String msg) {
        kafkaSampleTemplate.send(kafkaConfig.getMessageWriterTopicName(), msg)
            .whenComplete((result, err) -> {
                if (err == null) {
                    LOGGER.info("MESSAGE:: '{}' sent successfully.", msg);
                } else {
                    LOGGER.error("ERROR in getting message '{}' due to {}", msg, err.getMessage());
                }
            });
    }
}
