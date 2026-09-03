package org.example.fooddelivery.controller;

import org.example.fooddelivery.entity.OrderItem;
import org.example.fooddelivery.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public OrderItem createOrderItem(
            @RequestBody OrderItem item) {

        return orderItemService.createOrderItem(item);
    }

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(
            @PathVariable Long id) {

        return orderItemService.getOrderItemById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItem> getItemsByOrder(
            @PathVariable Long orderId) {

        return orderItemService.getItemsByOrder(orderId);
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItem(
            @PathVariable Long id,
            @RequestBody OrderItem item) {

        return orderItemService.updateOrderItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String deleteOrderItem(@PathVariable Long id) {

        orderItemService.deleteOrderItem(id);

        return "Order item deleted successfully";
    }
}