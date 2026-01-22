package com.example.REST_API_DELIVERY.repository;

import com.example.REST_API_DELIVERY.model.Dish;
import com.example.REST_API_DELIVERY.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long > {

    List<Dish> findByRestoran (Restoran restoran);

    List<Dish> findByRestoranAndAvailableTrue (Restoran restoran);
}
