package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.Customer;
import org.example.fooddelivery.repository.CustomerRepository;
import org.example.fooddelivery.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Customer register(Customer customer) {

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException(
                    "Email already registered"
            );
        }

        if (customer.getPhone() != null &&
                customerRepository.existsByPhone(customer.getPhone())) {

            throw new RuntimeException(
                    "Phone number already registered"
            );
        }

        // Encrypt password
        customer.setPassword(
                passwordEncoder.encode(customer.getPassword())
        );

        return customerRepository.save(customer);
    }

    public String login(String email, String password) {

        Customer customer = customerRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        )
                );

        // Check password
        if (!passwordEncoder.matches(
                password,
                customer.getPassword())) {

            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        // Generate JWT
        return jwtService.generateToken(
                customer.getEmail()
        );
    }
}