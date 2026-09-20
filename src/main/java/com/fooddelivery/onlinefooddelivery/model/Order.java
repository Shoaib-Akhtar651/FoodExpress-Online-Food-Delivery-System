
package com.fooddelivery.onlinefooddelivery.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;


    @NotBlank(message = "User ID is required")
    private String userId;


    @NotEmpty(message = "Order must contain at least one item")
    @Valid
    private List<OrderItem> items =
            new ArrayList<>();


    @PositiveOrZero(
        message = "Total amount cannot be negative"
    )
    private double totalAmount;


    @NotBlank(message = "Order status is required")
    private String status;


    @Valid
    private Address deliveryAddress;

    private String cancelReason;


    public String getId() {

        return id;
    }


    public void setId(String id) {

        this.id = id;
    }


    public String getUserId() {

        return userId;
    }


    public void setUserId(String userId) {

        this.userId = userId;
    }


    public List<OrderItem> getItems() {

        return items;
    }


    public void setItems(List<OrderItem> items) {

        this.items = items;
    }


    public double getTotalAmount() {

        return totalAmount;
    }


    public void setTotalAmount(double totalAmount) {

        this.totalAmount = totalAmount;
    }


    public String getStatus() {

        return status;
    }


    public void setStatus(String status) {

        this.status = status;
    }


    public Address getDeliveryAddress() {

        return deliveryAddress;
    }


    public void setDeliveryAddress(
            Address deliveryAddress) {

        this.deliveryAddress = deliveryAddress;
    }


    public String getCancelReason() {

        return cancelReason;
    }


    public void setCancelReason(
            String cancelReason) {

        this.cancelReason = cancelReason;
    }

}