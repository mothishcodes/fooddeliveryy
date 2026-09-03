package org.example.fooddelivery.repository;

import org.example.fooddelivery.entity.DeliveryDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryDetailsRepository
        extends JpaRepository<DeliveryDetails, Long> {

    Optional<DeliveryDetails> findByOrderId(Long orderId);
}
