package com.fooddelivery.onlinefooddelivery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fooddelivery.onlinefooddelivery.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {

}