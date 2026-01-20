package com.example.REST_API_DELIVERY.repository;

import com.example.REST_API_DELIVERY.model.Role;
import com.example.REST_API_DELIVERY.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
User findByUsername(String username);
List<User> findByRole(Role role);
}
