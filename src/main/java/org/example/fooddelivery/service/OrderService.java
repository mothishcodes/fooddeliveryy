package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.Order;
import org.example.fooddelivery.enums.OrderStatus;
import org.example.fooddelivery.repository.CartRepository;
import org.example.fooddelivery.repository.DeliveryDetailsRepository;
import org.example.fooddelivery.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderProducer orderProducer;
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;

    public OrderService(
            OrderRepository orderRepository,
            CartRepository cartRepository,
            DeliveryDetailsRepository deliveryDetailsRepository,
            OrderProducer orderProducer,
            NotificationService notificationService) {

        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
        this.notificationService =
                notificationService;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByRestaurant(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId);
    }

    public Order updateOrderStatus(
            Long id,
            OrderStatus status) {

        Order order = getOrderById(id);

        order.setStatus(status);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {

        Order order = getOrderById(id);

        orderRepository.delete(order);
    }
}