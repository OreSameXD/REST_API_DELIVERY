package com.example.REST_API_DELIVERY.controller;

import com.example.REST_API_DELIVERY.model.Restoran;
import com.example.REST_API_DELIVERY.service.RestoranService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestoranController {

    private final RestoranService restoranService;

    public RestoranController(RestoranService restoranService) {
        this.restoranService = restoranService;
    }

    @PostMapping
    public Restoran createRestoran(@RequestBody Restoran restoran) {
        return restoranService.createRestoran(restoran);
    }

    @GetMapping("/{id}")
    public Restoran getRestoranById(@PathVariable Long id) {
        return restoranService.getRestoranById(id);
    }

    @GetMapping
    public List<Restoran> getAllRestorans(
            @RequestParam(required = false) String kitchen,
            @RequestParam(required = false) Double minRating
    ) {
        if (kitchen != null || minRating != null) {
            return restoranService.filterRestorans(kitchen, minRating);
        }
        return restoranService.getAllRestoran();
    }
    @PutMapping("/{id}")
    public Restoran updateRestoran(@PathVariable Long id, @RequestBody Restoran updatedRestoran) {
        return restoranService.updateRestoran(id, updatedRestoran);
    }
}