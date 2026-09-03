package org.example.fooddelivery.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    private Double rating;

    private Boolean active = true;

    @OneToMany(mappedBy = "restaurant",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Menu> menuItems = new ArrayList<>();

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders = new ArrayList<>();
}