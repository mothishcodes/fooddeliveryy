package org.example.fooddelivery.service;

import org.example.fooddelivery.entity.OrderItem;
import org.example.fooddelivery.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem createOrderItem(OrderItem item) {
        return orderItemRepository.save(item);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order item not found"));
    }

    public List<OrderItem> getItemsByOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public OrderItem updateOrderItem(
            Long id,
            OrderItem updatedItem) {

        OrderItem item = getOrderItemById(id);

        item.setQuantity(updatedItem.getQuantity());
        item.setPrice(updatedItem.getPrice());

        return orderItemRepository.save(item);
    }

    public void deleteOrderItem(Long id) {

        OrderItem item = getOrderItemById(id);

        orderItemRepository.delete(item);
    }
}