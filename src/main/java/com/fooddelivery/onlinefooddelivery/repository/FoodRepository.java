package com.fooddelivery.onlinefooddelivery.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fooddelivery.onlinefooddelivery.model.Food;

public interface FoodRepository extends MongoRepository<Food, String> {

    List<Food> findByRestaurantId(String restaurantId);

    List<Food> findByCategoryId(String categoryId);
}