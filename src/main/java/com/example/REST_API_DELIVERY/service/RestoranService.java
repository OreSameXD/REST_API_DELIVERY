package com.example.REST_API_DELIVERY.service;

import com.example.REST_API_DELIVERY.model.Restoran;
import com.example.REST_API_DELIVERY.repository.RestoranRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestoranService {
    private RestoranRepository restoranRepository;
    public RestoranService (RestoranRepository restoranRepository){
        this.restoranRepository = restoranRepository;
    }

    public Restoran createRestoran(Restoran restoran){
        return restoranRepository.save(restoran);
    }
    public Restoran getRestoranById(Long id){
        return restoranRepository.findById(id).orElseThrow(()-> new RuntimeException("А не найдено"));
    }
    public List<Restoran> getAllRestoran(){
        return restoranRepository.findAll();
    }
    public Restoran updateRestoran(Long id,Restoran updatedRestoran){
        Restoran restoran = getRestoranById(id);
        restoran.setName(updatedRestoran.getName());
        restoran.setKitchen(updatedRestoran.getKitchen());
        restoran.setOpen(updatedRestoran.isOpen());
        return restoranRepository.save(restoran);
    }
    public List<Restoran> filterRestorans(String kitchen, Double minRating){
        List<Restoran> allRestorans= restoranRepository.findAll();
        List<Restoran> result = new ArrayList<>();
        for (Restoran r :allRestorans) {
            boolean matches = true;
            if (kitchen != null && !kitchen.isEmpty()) {
                if (!r.getKitchen().equalsIgnoreCase(kitchen)) {
                    matches = false;
                }
            }
            if (minRating != null) {
                if (r.getRating() < minRating) {
                    matches = false;
                }
            }
            if (matches) {
                result.add(r);
            }
        }
        return result;
}
}
