package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.Payment;
import org.example.fooddelivery.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    @PostMapping("/order/{orderId}")
    public ResponseEntity<Payment> pay(
            @PathVariable Long orderId,
            @RequestParam String method) {

        return ResponseEntity.ok(
                paymentService.makePayment(
                        orderId,
                        method)
        );
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Payment> getPayment(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                paymentService.getPayment(orderId)
        );
    }
}
