package com.fooddelivery.onlinefooddelivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fooddelivery.onlinefooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.onlinefooddelivery.model.Cart;
import com.fooddelivery.onlinefooddelivery.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart(Cart cart) {

        calculateTotal(cart);

        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {

        return cartRepository.findAll();
    }

    public Cart getCartById(String id) {

        return cartRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found with id: " + id
                        )
                );
    }

    public Cart getCartByUserId(String userId) {

        return cartRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found for user id: " + userId
                        )
                );
    }

    public Cart updateCart(String id, Cart cart) {

        Cart existingCart = cartRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found with id: " + id
                        )
                );

        existingCart.setUserId(cart.getUserId());
        existingCart.setItems(cart.getItems());

        calculateTotal(existingCart);

        return cartRepository.save(existingCart);
    }

    public void deleteCart(String id) {

        if (!cartRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Cart not found with id: " + id
            );
        }

        cartRepository.deleteById(id);
    }

    private void calculateTotal(Cart cart) {

        double total = 0.0;

        if (cart.getItems() != null) {

            for (com.fooddelivery.onlinefooddelivery.model.CartItem item : cart.getItems()) {

                total += item.getPrice() * item.getQuantity();
            }
        }

        cart.setTotalAmount(total);
    }
}