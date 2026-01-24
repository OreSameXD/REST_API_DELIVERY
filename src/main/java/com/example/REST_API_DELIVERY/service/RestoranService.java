package com.example.REST_API_DELIVERY.service;

import com.example.REST_API_DELIVERY.exception.ResourceNotFoundException;
import com.example.REST_API_DELIVERY.model.Restoran;
import com.example.REST_API_DELIVERY.repository.RestoranRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestoranService {
    private final RestoranRepository restoranRepository;
    public RestoranService (RestoranRepository restoranRepository){
        this.restoranRepository = restoranRepository;
    }

    public Restoran createRestoran(Restoran restoran){
        if (restoran.getName() == null || restoran.getName().isBlank()){
            throw new IllegalArgumentException("должно быть заполненым");
        }

        return restoranRepository.save(restoran);
    }
    public Restoran getRestoranById(Long id){
        return restoranRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("А не найдено с таким айди" + id));
    }

    public Restoran updateRestoran(Long id,Restoran updatedRestoran){
        Restoran restoran = getRestoranById(id);
        restoran.setName(updatedRestoran.getName());
        restoran.setKitchen(updatedRestoran.getKitchen());
        restoran.setOpen(updatedRestoran.isOpen());
        return restoranRepository.save(restoran);
    }

    public Page<Restoran> getAllRestorans(Pageable pageable) {
        return restoranRepository.findAll(pageable);
    }
    public Page<Restoran> filterRestorans(String kitchen, Double minRating ,Pageable pageable) {
        if (kitchen != null && !kitchen.isEmpty() && minRating != null) {
            return restoranRepository.findByKitchenIgnoreCaseAndRatingGreaterThanEqual(kitchen, minRating, pageable);

        } else if (kitchen != null && !kitchen.isEmpty()) {
            return restoranRepository.findByKitchen(kitchen, pageable);

        } else if (minRating != null) {
            return restoranRepository.findByRatingGreaterThanEqual(minRating, pageable);

        } else {
            return restoranRepository.findAll(pageable);
        }
    }
}

