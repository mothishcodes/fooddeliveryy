package org.example.fooddelivery.repository;

import org.example.fooddelivery.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository
        extends JpaRepository<Menu, Long> {

    List<Menu> findByRestaurantId(Long restaurantId);

    List<Menu> findByNameContainingIgnoreCase(String name);
}