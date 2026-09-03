package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.Customer;
import org.example.fooddelivery.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (customerRepository.existsByPhone(customer.getPhone())) {
            throw new RuntimeException("Phone already exists");
        }

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found: " + id));
    }

    public Customer updateCustomer(Long id,
                                   Customer updatedCustomer) {

        Customer customer = getCustomerById(id);

        customer.setName(updatedCustomer.getName());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setAddress(updatedCustomer.getAddress());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {

        if (!customerRepository.existsById(id)) {
            throw new RuntimeException(
                    "Customer not found: " + id);
        }

        customerRepository.deleteById(id);
    }
}
