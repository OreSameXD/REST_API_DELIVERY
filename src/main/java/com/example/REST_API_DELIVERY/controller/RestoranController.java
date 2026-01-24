package com.example.REST_API_DELIVERY.controller;

import com.example.REST_API_DELIVERY.model.Restoran;
import com.example.REST_API_DELIVERY.service.RestoranService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Restoran> getAllRestorans(
            @RequestParam(required = false) String kitchen,
            @RequestParam(required = false) Double minRating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "rating,desc") String[] sort
    ){
        Sort sortOrder = Sort.by(sort[0]);
        if (sort.length > 1 && sort[1].equalsIgnoreCase("desc")) {
            sortOrder = sortOrder.descending();
        } else {
            sortOrder = sortOrder.ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sortOrder);


        return restoranService.filterRestorans(kitchen, minRating, pageable);
    }

    @PutMapping("/{id}")
    public Restoran updateRestoran(@PathVariable Long id, @RequestBody Restoran updatedRestoran) {
        return restoranService.updateRestoran(id, updatedRestoran);
    }
}