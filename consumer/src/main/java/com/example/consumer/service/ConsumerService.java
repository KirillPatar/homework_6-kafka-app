package com.example.consumer.service;

import lombok.AllArgsConstructor;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class ConsumerService {

    private final KStream <String,String> kStreamTopic1;
    private final KStream <String,String> kStreamTopic2;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @PostConstruct
    public void consume() {
        consumeFromPartitionTopic();
        consumeFromNoPartitionTopic();
    }

    public void consumeFromNoPartitionTopic() {
        kStreamTopic1.foreach((key, value) -> LOGGER.info("Consumed message from no partition topic: {}", value));
    }

    public void consumeFromPartitionTopic() {
        kStreamTopic2.foreach((key, value) -> LOGGER.info("Consumed message from partitioned topic: {}", value));
    }
}
