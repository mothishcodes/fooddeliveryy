package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.CartItem;
import org.example.fooddelivery.service.CartItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public CartItem createCartItem(
            @RequestBody CartItem cartItem) {

        return cartItemService.createCartItem(cartItem);
    }

    @GetMapping
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id);
    }

    @GetMapping("/cart/{cartId}")
    public List<CartItem> getItemsByCart(
            @PathVariable Long cartId) {

        return cartItemService.getItemsByCart(cartId);
    }

    @PutMapping("/{id}")
    public CartItem updateCartItem(
            @PathVariable Long id,
            @RequestBody CartItem cartItem) {

        return cartItemService.updateCartItem(id, cartItem);
    }

    @DeleteMapping("/{id}")
    public String deleteCartItem(@PathVariable Long id) {

        cartItemService.deleteCartItem(id);

        return "Cart item deleted successfully";
    }
}