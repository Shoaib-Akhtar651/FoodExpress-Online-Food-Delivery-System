
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

import com.fooddelivery.onlinefooddelivery.model.Order;
import com.fooddelivery.onlinefooddelivery.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    private final OrderService orderService;


    public OrderController(
            OrderService orderService) {

        this.orderService = orderService;
    }


    @PostMapping
    public Order createOrder(
            @Valid @RequestBody Order order) {

        return orderService.createOrder(
                order
        );
    }


    @PostMapping("/from-cart/{userId}")
    public Order createOrderFromCart(
            @PathVariable String userId) {

        return orderService.createOrderFromCart(
                userId
        );
    }


    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }


    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(
            @PathVariable String userId) {

        return orderService.getOrdersByUserId(
                userId
        );
    }


    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(
            @PathVariable String status) {

        return orderService.getOrdersByStatus(
                status
        );
    }


    @GetMapping("/{id}")
    public Order getOrderById(
            @PathVariable String id) {

        return orderService.getOrderById(
                id
        );
    }


    @PutMapping("/{id}")
    public Order updateOrder(
            @PathVariable String id,
            @Valid @RequestBody Order order) {

        return orderService.updateOrder(
                id,
                order
        );
    }


    @PutMapping("/{id}/cancel")
    public Order cancelOrder(
            @PathVariable String id,
            @RequestBody String cancelReason) {

     
        if (cancelReason != null) {

            cancelReason =
                    cancelReason.trim();


            if (cancelReason.startsWith("\"")
                    && cancelReason.endsWith("\"")
                    && cancelReason.length() >= 2) {

                cancelReason =
                        cancelReason.substring(
                                1,
                                cancelReason.length() - 1
                        );
            }
        }


        return orderService.cancelOrder(
                id,
                cancelReason
        );
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(
            @PathVariable String id) {

        orderService.deleteOrder(
                id
        );
    }

}