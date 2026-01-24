package com.example.REST_API_DELIVERY.repository;

import com.example.REST_API_DELIVERY.model.Order;
import com.example.REST_API_DELIVERY.model.OrderStatus;
import com.example.REST_API_DELIVERY.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser(User user);
    List<Order> findByStatus(OrderStatus orderStatus);

}
