package com.fooddelivery.onlinefooddelivery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fooddelivery.onlinefooddelivery.model.Restaurant;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

}