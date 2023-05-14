package com.example.producer.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerService {

    public static final String TOPIC = "test_topic_no_partitions";
    public static final String TOPIC_2 = "test_topic_with_partitions";

    private final KafkaTemplate <String,String> kafkaTemplate;

    public void sendMessage (String message) {
        this.kafkaTemplate.send(TOPIC, message);
        this.kafkaTemplate.send(TOPIC_2, message + " - from topic 2");
    }

}
