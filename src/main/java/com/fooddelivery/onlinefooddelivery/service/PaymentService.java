package com.fooddelivery.onlinefooddelivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.onlinefooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.onlinefooddelivery.model.Order;
import com.fooddelivery.onlinefooddelivery.model.Payment;
import com.fooddelivery.onlinefooddelivery.repository.OrderRepository;
import com.fooddelivery.onlinefooddelivery.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderRepository orderRepository) {

        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    public Payment createPayment(Payment payment) {


        Order order = orderRepository.findById(payment.getOrderId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: "
                                        + payment.getOrderId()
                        )
                );



        if (payment.getAmount() != order.getTotalAmount()) {

            throw new RuntimeException(
                    "Payment amount does not match order total amount"
            );
        }



        if (payment.getPaymentStatus() == null ||
                payment.getPaymentStatus().isBlank()) {

            payment.setPaymentStatus("SUCCESS");
        }


        Payment savedPayment =
                paymentRepository.save(payment);



        if ("SUCCESS".equalsIgnoreCase(
                savedPayment.getPaymentStatus())) {

            order.setStatus("PAID");

            orderRepository.save(order);
        }


        return savedPayment;
    }


    public List<Payment> getAllPayments() {

        return paymentRepository.findAll();
    }


    public Payment getPaymentById(String id) {

        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found with id: " + id
                        )
                );
    }


    public List<Payment> getPaymentsByUserId(
            String userId) {

        return paymentRepository.findByUserId(userId);
    }


    public Payment getPaymentByOrderId(
            String orderId) {

        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payment not found for order id: "
                                        + orderId
                        )
                );
    }


    public Payment updatePayment(
            String id,
            Payment payment) {

        Payment existingPayment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Payment not found with id: "
                                                + id
                                )
                        );


        existingPayment.setOrderId(
                payment.getOrderId()
        );

        existingPayment.setUserId(
                payment.getUserId()
        );

        existingPayment.setAmount(
                payment.getAmount()
        );

        existingPayment.setPaymentMethod(
                payment.getPaymentMethod()
        );

        existingPayment.setPaymentStatus(
                payment.getPaymentStatus()
        );


        Payment updatedPayment =
                paymentRepository.save(existingPayment);


        if ("SUCCESS".equalsIgnoreCase(
                updatedPayment.getPaymentStatus())) {

            Order order =
                    orderRepository.findById(
                            updatedPayment.getOrderId()
                    )
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Order not found with id: "
                                            + updatedPayment.getOrderId()
                            )
                    );


            order.setStatus("PAID");

            orderRepository.save(order);
        }


        return updatedPayment;
    }


    public void deletePayment(String id) {

        if (!paymentRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Payment not found with id: " + id
            );
        }


        paymentRepository.deleteById(id);
    }
}