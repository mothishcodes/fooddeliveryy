package org.example.fooddelivery.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_partners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phone;

    private Boolean available = true;

    private Double latitude;

    private Double longitude;

    @OneToMany(mappedBy = "deliveryPartner")
    private List<DeliveryDetails> deliveries = new ArrayList<>();
}
