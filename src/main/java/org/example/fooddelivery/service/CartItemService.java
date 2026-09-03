package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.CartItem;
import org.example.fooddelivery.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(Long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cart item not found"));
    }

    public List<CartItem> getItemsByCart(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public CartItem updateCartItem(Long id, CartItem updatedItem) {

        CartItem item = getCartItemById(id);

        item.setQuantity(updatedItem.getQuantity());
        item.setPrice(updatedItem.getPrice());

        return cartItemRepository.save(item);
    }

    public void deleteCartItem(Long id) {

        CartItem item = getCartItemById(id);

        cartItemRepository.delete(item);
    }
}