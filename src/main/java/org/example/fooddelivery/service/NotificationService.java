package org.example.fooddelivery.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(
            SimpMessagingTemplate messagingTemplate) {

        this.messagingTemplate = messagingTemplate;
    }

    public void sendOrderStatus(
            Long orderId,
            String status) {

        String message =
                "Order " + orderId +
                        " status: " + status;

        messagingTemplate.convertAndSend(
                "/topic/order/" + orderId,
                message
        );
    }
}
