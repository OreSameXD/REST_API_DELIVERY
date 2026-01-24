package com.example.REST_API_DELIVERY.repository;

import com.example.REST_API_DELIVERY.model.Dish;
import com.example.REST_API_DELIVERY.model.Restoran;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran,Long> {
    Page<Restoran> findByKitchen (String kitchen , Pageable pageable);

    Page<Restoran>findByIsOpenTrue (Pageable pageable);

    Page<Restoran> findByRatingGreaterThanEqual(Double minRating, Pageable pageable);

    Page<Restoran> findByKitchenIgnoreCaseAndRatingGreaterThanEqual(String kitchen, Double minRating, Pageable pageable);
}
