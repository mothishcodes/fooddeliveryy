package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.DeliveryDetails;
import org.example.fooddelivery.repository.DeliveryDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryDetailsService {

    private final DeliveryDetailsRepository repository;

    public DeliveryDetailsService(
            DeliveryDetailsRepository repository) {

        this.repository = repository;
    }

    public DeliveryDetails createDelivery(
            DeliveryDetails details) {

        return repository.save(details);
    }

    public List<DeliveryDetails> getAllDeliveries() {
        return repository.findAll();
    }

    public DeliveryDetails getDeliveryById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Delivery details not found"));
    }

    public DeliveryDetails getDeliveryByOrder(Long orderId) {

        return repository.findByOrderId(orderId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Delivery details not found"));
    }

    public DeliveryDetails updateDelivery(
            Long id,
            DeliveryDetails updatedDetails) {

        DeliveryDetails details = getDeliveryById(id);

        details.setDeliveryAddress(
                updatedDetails.getDeliveryAddress());

        details.setEstimatedDeliveryTime(
                updatedDetails.getEstimatedDeliveryTime());

        details.setDeliveredAt(
                updatedDetails.getDeliveredAt());

        details.setStatus(
                updatedDetails.getStatus());

        return repository.save(details);
    }

    public void deleteDelivery(Long id) {

        DeliveryDetails details = getDeliveryById(id);

        repository.delete(details);
    }
}