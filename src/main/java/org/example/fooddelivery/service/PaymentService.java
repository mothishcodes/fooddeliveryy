package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.Order;
import org.example.fooddelivery.entity.Payment;
import org.example.fooddelivery.enums.OrderStatus;
import org.example.fooddelivery.repository.OrderRepository;
import org.example.fooddelivery.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository) {

        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public Payment makePayment(
            Long orderId,
            String method) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"));

        if (paymentRepository
                .findByOrderId(orderId)
                .isPresent()) {

            throw new RuntimeException(
                    "Payment already made");
        }

        Payment payment = new Payment();

        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setMethod(method);
        payment.setStatus("SUCCESS");
        payment.setPaymentDate(
                LocalDateTime.now()
        );

        order.setStatus(OrderStatus.CONFIRMED);

        orderRepository.save(order);

        return paymentRepository.save(payment);
    }

    public Payment getPayment(Long orderId) {

        return paymentRepository
                .findByOrderId(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Payment not found"));
    }
}