
package com.fooddelivery.onlinefooddelivery.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fooddelivery.onlinefooddelivery.model.Order;

public interface OrderRepository
        extends MongoRepository<Order, String> {

    List<Order> findByUserId(
            String userId
    );


    List<Order> findByStatus(
            String status
    );

}