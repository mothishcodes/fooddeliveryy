package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.DeliveryDetails;
import org.example.fooddelivery.service.DeliveryDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryDetailsController {

    private final DeliveryDetailsService service;

    public DeliveryDetailsController(
            DeliveryDetailsService service) {

        this.service = service;
    }

    @PostMapping
    public DeliveryDetails createDelivery(
            @RequestBody DeliveryDetails details) {

        return service.createDelivery(details);
    }

    @GetMapping
    public List<DeliveryDetails> getAllDeliveries() {
        return service.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public DeliveryDetails getDeliveryById(
            @PathVariable Long id) {

        return service.getDeliveryById(id);
    }

    @GetMapping("/order/{orderId}")
    public DeliveryDetails getDeliveryByOrder(
            @PathVariable Long orderId) {

        return service.getDeliveryByOrder(orderId);
    }

    @PutMapping("/{id}")
    public DeliveryDetails updateDelivery(
            @PathVariable Long id,
            @RequestBody DeliveryDetails details) {

        return service.updateDelivery(id, details);
    }

    @DeleteMapping("/{id}")
    public String deleteDelivery(@PathVariable Long id) {

        service.deleteDelivery(id);

        return "Delivery details deleted successfully";
    }
}