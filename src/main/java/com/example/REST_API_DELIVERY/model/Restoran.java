package com.example.REST_API_DELIVERY.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restorans")
public class Restoran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String kitchen;
    private Double rating = 0.0;
    private boolean isOpen = true;
    public Restoran (String name, String kitchen){
        this.name = name;
        this.kitchen = kitchen;
    }
    public Restoran(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
