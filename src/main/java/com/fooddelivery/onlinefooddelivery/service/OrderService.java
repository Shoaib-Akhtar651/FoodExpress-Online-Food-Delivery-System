
package com.fooddelivery.onlinefooddelivery.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.onlinefooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.onlinefooddelivery.model.Cart;
import com.fooddelivery.onlinefooddelivery.model.CartItem;
import com.fooddelivery.onlinefooddelivery.model.Order;
import com.fooddelivery.onlinefooddelivery.model.OrderItem;
import com.fooddelivery.onlinefooddelivery.model.User;
import com.fooddelivery.onlinefooddelivery.repository.OrderRepository;
import com.fooddelivery.onlinefooddelivery.repository.UserRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final CartService cartService;

    private final UserRepository userRepository;


    public OrderService(
            OrderRepository orderRepository,
            CartService cartService,
            UserRepository userRepository) {

        this.orderRepository = orderRepository;

        this.cartService = cartService;

        this.userRepository = userRepository;
    }


    public Order createOrder(Order order) {

        calculateOrderTotal(order);


        if (order.getStatus() == null
                || order.getStatus().isBlank()) {

            order.setStatus(
                    "PENDING"
            );
        }


        if (order.getDeliveryAddress() == null
                && order.getUserId() != null
                && !order.getUserId().isBlank()) {

            User user =
                    userRepository.findById(
                            order.getUserId()
                    )

                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "User not found with id: "
                                            + order.getUserId()
                            )
                    );


            order.setDeliveryAddress(
                    user.getAddress()
            );
        }


        order.setCancelReason(
                null
        );


        return orderRepository.save(
                order
        );
    }


    public Order createOrderFromCart(
            String userId) {

        Cart cart =
                cartService.getCartByUserId(
                        userId
                );


        if (cart.getItems() == null
                || cart.getItems().isEmpty()) {

            throw new IllegalStateException(
                    "Cannot create order because cart is empty"
            );
        }

        User user =
                userRepository.findById(
                        userId
                )

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: "
                                        + userId
                        )
                );


        Order order =
                new Order();


        order.setUserId(
                userId
        );


        List<OrderItem> orderItems =
                new ArrayList<>();


        for (CartItem cartItem :
                cart.getItems()) {

            OrderItem orderItem =
                    new OrderItem();


            orderItem.setFoodId(
                    cartItem.getFoodId()
            );


            orderItem.setFoodName(
                    cartItem.getFoodName()
            );


            orderItem.setPrice(
                    cartItem.getPrice()
            );


            orderItem.setQuantity(
                    cartItem.getQuantity()
            );


            double subtotal =
                    cartItem.getPrice()
                    * cartItem.getQuantity();


            orderItem.setSubtotal(
                    subtotal
            );


            orderItems.add(
                    orderItem
            );
        }


        order.setItems(
                orderItems
        );


        order.setTotalAmount(
                cart.getTotalAmount()
        );


        order.setStatus(
                "PENDING"
        );


        order.setDeliveryAddress(
                user.getAddress()
        );

        order.setCancelReason(
                null
        );


        Order savedOrder =
                orderRepository.save(
                        order
                );


        if (cart.getId() != null
                && !cart.getId().isBlank()) {

            cartService.deleteCart(
                    cart.getId()
            );
        }


        return savedOrder;
    }


    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }


    public Order getOrderById(
            String id) {

        return orderRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: "
                                        + id
                        )
                );
    }


    public List<Order> getOrdersByUserId(
            String userId) {

        return orderRepository.findByUserId(
                userId
        );
    }


    public List<Order> getOrdersByStatus(
            String status) {

        return orderRepository.findByStatus(
                status
        );
    }


    public Order updateOrder(
            String id,
            Order order) {

        Order existingOrder =
                orderRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Order not found with id: "
                                                + id
                                )
                        );


        existingOrder.setUserId(
                order.getUserId()
        );


        existingOrder.setItems(
                order.getItems()
        );


        existingOrder.setTotalAmount(
                order.getTotalAmount()
        );


        existingOrder.setStatus(
                order.getStatus()
        );


        existingOrder.setDeliveryAddress(
                order.getDeliveryAddress()
        );


        existingOrder.setCancelReason(
                order.getCancelReason()
        );


        return orderRepository.save(
                existingOrder
        );
    }


    public Order cancelOrder(
            String id,
            String cancelReason) {


        Order existingOrder =
                orderRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Order not found with id: "
                                                + id
                                )
                        );


        if (cancelReason == null
                || cancelReason.isBlank()) {

            throw new IllegalArgumentException(
                    "Cancellation reason is required"
            );
        }


        cancelReason =
                cancelReason.trim();


        if (!isValidCancelReason(
                cancelReason)) {

            throw new IllegalArgumentException(
                    "Invalid cancellation reason"
            );
        }


        if ("CANCELLED".equalsIgnoreCase(
                existingOrder.getStatus())) {

            throw new IllegalStateException(
                    "Order is already cancelled"
            );
        }

        if ("DELIVERED".equalsIgnoreCase(
                existingOrder.getStatus())

                ||

            "COMPLETED".equalsIgnoreCase(
                existingOrder.getStatus())) {

            throw new IllegalStateException(
                    "Delivered or completed order cannot be cancelled"
            );
        }


        existingOrder.setStatus(
                "CANCELLED"
        );


        existingOrder.setCancelReason(
                cancelReason
        );

        return orderRepository.save(
                existingOrder
        );
    }


    private boolean isValidCancelReason(
            String reason) {

        return reason.equals(
                    "Ordered by mistake"
                )

                ||

                reason.equals(
                    "Food delivery is taking too long"
                )

                ||

                reason.equals(
                    "Changed my mind"
                )

                ||

                reason.equals(
                    "Ordered from another restaurant"
                )

                ||

                reason.equals(
                    "Other reason"
                );
    }


    public void deleteOrder(
            String id) {

        if (!orderRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Order not found with id: "
                            + id
            );
        }


        orderRepository.deleteById(
                id
        );
    }


    private void calculateOrderTotal(
            Order order) {

        double total = 0.0;


        if (order.getItems() != null) {

            for (OrderItem item :
                    order.getItems()) {

                double subtotal =
                        item.getPrice()
                        * item.getQuantity();


                item.setSubtotal(
                        subtotal
                );


                total += subtotal;
            }
        }


        order.setTotalAmount(
                total
        );
    }

}