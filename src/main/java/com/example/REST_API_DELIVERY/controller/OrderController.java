package com.example.REST_API_DELIVERY.controller;

import com.example.REST_API_DELIVERY.model.Order;
import com.example.REST_API_DELIVERY.model.OrderStatus;
import com.example.REST_API_DELIVERY.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController (OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping
    public Order createOrder(@RequestParam Long userId){

        return orderService.createOrder(userId);
    }
    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id){

        return orderService.getOrder(id);
    }
    @GetMapping
    public List<Order> getOrders(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) OrderStatus status
    ) {
        if (userId != null) {
            return orderService.getOrdersByUser(userId);
        }
        if (status != null) {
            return orderService.getOrdersByStatus(status);
        }
        throw new RuntimeException("Укажите userId или status");
    }

    @PatchMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id,
                              @RequestParam OrderStatus status) {
        return orderService.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return "Заказ отменён";
    }

}
