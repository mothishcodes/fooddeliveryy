package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.Restaurant;
import org.example.fooddelivery.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(
            RestaurantService restaurantService) {

        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(
            @RequestBody Restaurant restaurant) {

        return new ResponseEntity<>(
                restaurantService.createRestaurant(restaurant),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>>
    getAllRestaurants() {

        return ResponseEntity.ok(
                restaurantService.getAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurant(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                restaurantService.getRestaurantById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>>
    searchRestaurant(@RequestParam String name) {

        return ResponseEntity.ok(
                restaurantService.searchRestaurant(name));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(
            @PathVariable Long id,
            @RequestBody Restaurant restaurant) {

        return ResponseEntity.ok(
                restaurantService.updateRestaurant(
                        id, restaurant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(
            @PathVariable Long id) {

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.ok(
                "Restaurant deleted successfully");
    }
}
