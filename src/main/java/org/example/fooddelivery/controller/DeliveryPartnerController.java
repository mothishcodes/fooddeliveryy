package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.DeliveryPartner;
import org.example.fooddelivery.service.DeliveryPartnerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-partners")
public class DeliveryPartnerController {

    private final DeliveryPartnerService service;

    public DeliveryPartnerController(
            DeliveryPartnerService service) {

        this.service = service;
    }

    @PostMapping
    public DeliveryPartner createPartner(
            @RequestBody DeliveryPartner partner) {

        return service.createPartner(partner);
    }

    @GetMapping
    public List<DeliveryPartner> getAllPartners() {
        return service.getAllPartners();
    }

    @GetMapping("/{id}")
    public DeliveryPartner getPartnerById(
            @PathVariable Long id) {

        return service.getPartnerById(id);
    }

    @GetMapping("/available")
    public List<DeliveryPartner> getAvailablePartners() {
        return service.getAvailablePartners();
    }

    @PutMapping("/{id}")
    public DeliveryPartner updatePartner(
            @PathVariable Long id,
            @RequestBody DeliveryPartner partner) {

        return service.updatePartner(id, partner);
    }

    @DeleteMapping("/{id}")
    public String deletePartner(@PathVariable Long id) {

        service.deletePartner(id);

        return "Delivery partner deleted successfully";
    }
}