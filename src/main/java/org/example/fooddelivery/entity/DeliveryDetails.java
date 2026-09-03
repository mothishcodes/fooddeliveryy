package org.example.fooddelivery.entity;


import jakarta.persistence.*;
import lombok.*;
import org.example.fooddelivery.enums.DeliveryStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deliveryAddress;

    private LocalDateTime estimatedDeliveryTime;

    private LocalDateTime deliveredAt;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private DeliveryStatus status;

    @OneToOne
    @JoinColumn(name = "order_id",
            nullable = false,
            unique = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_partner_id")
    private DeliveryPartner deliveryPartner;
}
