package org.example.fooddelivery.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(
            topics = "food-order-topic",
            groupId = "food-delivery-group"
    )
    public void consumeOrder(String message) {

        System.out.println(
                "Kafka message received: " + message
        );
    }
}
