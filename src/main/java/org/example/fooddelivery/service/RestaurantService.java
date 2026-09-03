package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.Restaurant;
import org.example.fooddelivery.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RedisService redisService;
    private final RestaurantRepository restaurantRepository;


    public RestaurantService(
            RestaurantRepository restaurantRepository,
            RedisService redisService) {

        this.restaurantRepository = restaurantRepository;
        this.redisService = redisService;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {

        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {

        return restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Restaurant not found: " + id));
    }

    public List<Restaurant> searchRestaurant(String name) {

        return restaurantRepository
                .findByNameContainingIgnoreCase(name);
    }

    public Restaurant updateRestaurant(
            Long id,
            Restaurant updated) {

        Restaurant restaurant =
                getRestaurantById(id);

        restaurant.setName(updated.getName());
        restaurant.setAddress(updated.getAddress());
        restaurant.setPhone(updated.getPhone());
        restaurant.setRating(updated.getRating());
        restaurant.setActive(updated.getActive());

        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {

        if (!restaurantRepository.existsById(id)) {
            throw new RuntimeException(
                    "Restaurant not found: " + id);
        }

        restaurantRepository.deleteById(id);
    }
}