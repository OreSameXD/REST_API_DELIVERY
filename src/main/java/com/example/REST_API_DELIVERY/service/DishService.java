package com.example.REST_API_DELIVERY.service;

import com.example.REST_API_DELIVERY.exception.ResourceNotFoundException;
import com.example.REST_API_DELIVERY.model.Dish;
import com.example.REST_API_DELIVERY.model.Restoran;
import com.example.REST_API_DELIVERY.repository.DishRepository;
import com.example.REST_API_DELIVERY.repository.RestoranRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {

    private DishRepository dishRepository;
    private RestoranRepository restoranRepository;

    public DishService(DishRepository dishRepository, RestoranRepository restoranRepository) {
        this.dishRepository = dishRepository;
        this.restoranRepository = restoranRepository;
    }

    public Dish createDish(Long restoranId, Dish dish) {
        Restoran restoran = restoranRepository.findById(restoranId).orElseThrow(() -> new ResourceNotFoundException("Ресторан не найден"));
        dish.setRestoran(restoran);
        return dishRepository.save(dish);
    }

    public List<Dish> getDishesByRestoran(Long restoranId) {
        Restoran restoran = restoranRepository.findById(restoranId).orElseThrow(() -> new ResourceNotFoundException("Ресторан не найден"));
        return dishRepository.findByRestoran(restoran);
    }

    public Dish updateDish(Long id, Dish updatedDish) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Блюдо не найдено"));
        dish.setName(updatedDish.getName());
        dish.setPrice(updatedDish.getPrice());
        dish.setAvailable(updatedDish.isAvailable());
        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Блюдо не найдено"));
        dishRepository.delete(dish);
    }

    public List<Dish> getAvailableDishes(Long restoranId) {
        Restoran restoran = restoranRepository.findById(restoranId).orElseThrow(() -> new ResourceNotFoundException("Ресторан не найден"));
        List<Dish> allDishes = dishRepository.findByRestoran(restoran);
        List<Dish> available = new ArrayList<>();
        for (Dish disha : allDishes) {
            if (disha.isAvailable()) {
                available.add(disha);
            }
        }
        return available;
    }
}