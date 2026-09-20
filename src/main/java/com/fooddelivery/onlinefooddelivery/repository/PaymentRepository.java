package com.fooddelivery.onlinefooddelivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fooddelivery.onlinefooddelivery.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByUserId(String userId);

    Optional<Payment> findByOrderId(String orderId);
}