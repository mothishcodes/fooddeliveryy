package org.example.fooddelivery.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(Long orderId) {

        String message = "Order placed: " + orderId;

        kafkaTemplate.send(
                "food-order-topic",
                message
        );

        System.out.println("Kafka message sent: " + message);
    }
}