package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.DeliveryPartner;
import org.example.fooddelivery.repository.DeliveryPartnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPartnerService {

    private final DeliveryPartnerRepository repository;

    public DeliveryPartnerService(DeliveryPartnerRepository repository) {
        this.repository = repository;
    }

    public DeliveryPartner createPartner(DeliveryPartner partner) {
        return repository.save(partner);
    }

    public List<DeliveryPartner> getAllPartners() {
        return repository.findAll();
    }

    public DeliveryPartner getPartnerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Delivery partner not found"));
    }

    public List<DeliveryPartner> getAvailablePartners() {
        return repository.findByAvailableTrue();
    }

    public DeliveryPartner updatePartner(
            Long id,
            DeliveryPartner updatedPartner) {

        DeliveryPartner partner = getPartnerById(id);

        partner.setName(updatedPartner.getName());
        partner.setPhone(updatedPartner.getPhone());
        partner.setAvailable(updatedPartner.getAvailable());
        partner.setLatitude(updatedPartner.getLatitude());
        partner.setLongitude(updatedPartner.getLongitude());

        return repository.save(partner);
    }

    public void deletePartner(Long id) {

        DeliveryPartner partner = getPartnerById(id);

        repository.delete(partner);
    }
}
