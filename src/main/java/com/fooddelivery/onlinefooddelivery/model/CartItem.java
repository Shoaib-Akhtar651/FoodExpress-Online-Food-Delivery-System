package com.fooddelivery.onlinefooddelivery.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class CartItem {

    @NotBlank(message = "Food ID is required")
    private String foodId;

    @NotBlank(message = "Food name is required")
    private String foodName;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private double price;

    @Positive(message = "Quantity must be greater than 0")
    private int quantity;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}