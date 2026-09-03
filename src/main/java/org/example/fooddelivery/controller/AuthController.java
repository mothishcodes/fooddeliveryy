package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.Customer;
import org.example.fooddelivery.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(
            @RequestBody Customer customer) {

        Customer savedCustomer =
                authService.register(customer);

        return ResponseEntity.ok(savedCustomer);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam String email,
            @RequestParam String password) {

        String token =
                authService.login(email, password);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "token", token
                )
        );
    }
}