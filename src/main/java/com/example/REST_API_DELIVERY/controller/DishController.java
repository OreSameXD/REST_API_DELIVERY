package com.example.REST_API_DELIVERY.controller;

import com.example.REST_API_DELIVERY.model.Dish;
import com.example.REST_API_DELIVERY.service.DishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }
    @PostMapping("/restaurant/{restoranId}")
    public Dish createDish(@PathVariable Long restoranId, @RequestBody Dish dish) {
        return dishService.createDish(restoranId, dish);
    }
    @GetMapping("/restaurant/{restoranId}")
    public List<Dish> getDishesByRestoran(@PathVariable Long restoranId) {
        return dishService.getDishesByRestoran(restoranId);
    }
    @GetMapping("/restaurant/{restoranId}/available")
    public List<Dish> getAvailableDishes(@PathVariable Long restoranId) {
        return dishService.getAvailableDishes(restoranId);
    }
    @PutMapping("/{id}")
    public Dish updateDish(@PathVariable Long id, @RequestBody Dish updatedDish) {
        return dishService.updateDish(id, updatedDish);
    }
    @DeleteMapping("/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "Блюдо удалено";
    }
}