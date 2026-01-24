package com.example.REST_API_DELIVERY.repository;

import com.example.REST_API_DELIVERY.model.Cart;
import com.example.REST_API_DELIVERY.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUserAndActiveTrue(User user);
}
