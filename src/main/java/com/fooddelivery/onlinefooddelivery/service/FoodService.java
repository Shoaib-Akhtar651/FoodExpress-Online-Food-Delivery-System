package com.fooddelivery.onlinefooddelivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.onlinefooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.onlinefooddelivery.model.Food;
import com.fooddelivery.onlinefooddelivery.repository.FoodRepository;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFoodById(String id) {
        return foodRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Food not found with id: " + id
                        )
                );
    }

    public List<Food> getFoodsByRestaurantId(String restaurantId) {
        return foodRepository.findByRestaurantId(restaurantId);
    }

    public List<Food> getFoodsByCategoryId(String categoryId) {
        return foodRepository.findByCategoryId(categoryId);
    }

    public Food updateFood(String id, Food food) {

        Food existingFood = foodRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Food not found with id: " + id
                        )
                );

        existingFood.setName(food.getName());
        existingFood.setDescription(food.getDescription());
        existingFood.setPrice(food.getPrice());
        existingFood.setRestaurantId(food.getRestaurantId());
        existingFood.setCategoryId(food.getCategoryId());
        existingFood.setAvailable(food.isAvailable());

        return foodRepository.save(existingFood);
    }

    public void deleteFood(String id) {

        if (!foodRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Food not found with id: " + id
            );
        }

        foodRepository.deleteById(id);
    }
}