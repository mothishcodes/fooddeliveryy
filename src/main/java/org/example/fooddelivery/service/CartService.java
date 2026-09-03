package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.Cart;
import org.example.fooddelivery.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart getCartByCustomer(Long customerId) {
        return cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart updateCart(Long id, Cart updatedCart) {

        Cart cart = getCartById(id);

        cart.setTotalAmount(updatedCart.getTotalAmount());

        return cartRepository.save(cart);
    }

    public void clearCart(Long id) {

        Cart cart = getCartById(id);

        cart.getItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);

        cartRepository.save(cart);
    }

    public void deleteCart(Long id) {
        Cart cart = getCartById(id);
        cartRepository.delete(cart);
    }
}