package com.example.producer;

import com.example.producer.controller.ProducerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class ProducerApplicationTests {

    @Autowired
    private ProducerController producer;

    private String consumedMessage;

    @KafkaListener(topics = "${test.topic}")
    public void listen(String message) {
        this.consumedMessage = message;
    }

    @Test
    public void givenEmbeddedKafkaBroker_whenSendingWithSimpleProducer_thenMessageReceived(){
        String data = "Sending with our own simple KafkaProducer";
        producer.sendMessageToKafkaTopic(data);
        await().atMost(5, SECONDS).untilAsserted(() -> assertEquals(data, consumedMessage));
    }

}
