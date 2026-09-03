package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.Cart;
import org.example.fooddelivery.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @GetMapping("/customer/{customerId}")
    public Cart getCartByCustomer(
            @PathVariable Long customerId) {

        return cartService.getCartByCustomer(customerId);
    }

    @PutMapping("/{id}")
    public Cart updateCart(
            @PathVariable Long id,
            @RequestBody Cart cart) {

        return cartService.updateCart(id, cart);
    }

    @DeleteMapping("/{id}/clear")
    public String clearCart(@PathVariable Long id) {

        cartService.clearCart(id);

        return "Cart cleared successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteCart(@PathVariable Long id) {

        cartService.deleteCart(id);

        return "Cart deleted successfully";
    }
}