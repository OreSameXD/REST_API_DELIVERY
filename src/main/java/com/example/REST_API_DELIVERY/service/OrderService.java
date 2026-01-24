package com.example.REST_API_DELIVERY.service;

import com.example.REST_API_DELIVERY.exception.ResourceNotFoundException;
import com.example.REST_API_DELIVERY.model.Cart;
import com.example.REST_API_DELIVERY.model.Order;
import com.example.REST_API_DELIVERY.model.OrderStatus;
import com.example.REST_API_DELIVERY.model.User;
import com.example.REST_API_DELIVERY.repository.OrderRepository;
import com.example.REST_API_DELIVERY.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        CartService cartService,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }
    public Order createOrder(Long userId) {
        Cart cart = cartService.getCart(userId);
        if (cart.getItems().isEmpty()) {
            throw new ResourceNotFoundException("Корзина пустая");
        }
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setRestoran(cart.getRestoran());
        order.setItems(cart.getItems());
        order.setStatus(OrderStatus.New);
        Order saved = orderRepository.save(order);
        cartService.clearCart(userId);
        return saved;
    }


    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Заказ не найден"));
    }


    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return orderRepository.findByUser(user);
    }


    public Order updateStatus(Long orderId, OrderStatus status) {
        Order order = getOrder(orderId);
        order.setStatus(status);
        return orderRepository.save(order);
    }


    public void cancelOrder(Long orderId) {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.Cancelled);
        orderRepository.save(order);
    }


    public List<Order> getOrdersByStatus(OrderStatus status) {

        return orderRepository.findByStatus(status);
    }
}
