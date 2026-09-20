package com.fooddelivery.onlinefooddelivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.onlinefooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.onlinefooddelivery.model.Restaurant;
import com.fooddelivery.onlinefooddelivery.repository.RestaurantRepository;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Restaurant not found with id: " + id
                        )
                );
    }

    public Restaurant updateRestaurant(String id, Restaurant restaurant) {

        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Restaurant not found with id: " + id
                        )
                );

        existingRestaurant.setName(restaurant.getName());
        existingRestaurant.setDescription(restaurant.getDescription());
        existingRestaurant.setAddress(restaurant.getAddress());
        existingRestaurant.setPhone(restaurant.getPhone());

        return restaurantRepository.save(existingRestaurant);
    }

    public void deleteRestaurant(String id) {

        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Restaurant not found with id: " + id
            );
        }

        restaurantRepository.deleteById(id);
    }
}