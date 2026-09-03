package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.Order;
import org.example.fooddelivery.enums.OrderStatus;
import org.example.fooddelivery.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(
            @PathVariable Long customerId) {

        return orderService.getOrdersByCustomer(customerId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> getOrdersByRestaurant(
            @PathVariable Long restaurantId) {

        return orderService.getOrdersByRestaurant(restaurantId);
    }

    @PutMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status) {

        return orderService.updateOrderStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {

        orderService.deleteOrder(id);

        return "Order deleted successfully";
    }


}
