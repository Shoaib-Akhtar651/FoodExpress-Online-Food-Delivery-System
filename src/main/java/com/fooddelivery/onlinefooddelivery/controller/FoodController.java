package com.fooddelivery.onlinefooddelivery.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.onlinefooddelivery.model.Food;
import com.fooddelivery.onlinefooddelivery.service.FoodService;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public Food createFood(@Valid @RequestBody Food food) {
        return foodService.createFood(food);
    }

    @GetMapping
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public Food getFoodById(@PathVariable String id) {
        return foodService.getFoodById(id);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Food> getFoodsByRestaurantId(@PathVariable String restaurantId) {
        return foodService.getFoodsByRestaurantId(restaurantId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Food> getFoodsByCategoryId(@PathVariable String categoryId) {
        return foodService.getFoodsByCategoryId(categoryId);
    }

    @PutMapping("/{id}")
    public Food updateFood(
            @PathVariable String id,
            @Valid @RequestBody Food food) {
        return foodService.updateFood(id, food);
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable String id) {
        foodService.deleteFood(id);
    }
}