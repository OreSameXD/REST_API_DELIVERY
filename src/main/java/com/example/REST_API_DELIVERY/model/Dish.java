package com.example.REST_API_DELIVERY.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dishes")
public class Dish {
    @ManyToOne
    @JoinColumn(name = "restoran_id", nullable = false)
    private Restoran restoran;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private boolean available = true;

    public Dish(){

    }
    public Dish(String name, Double price, Restoran restoran){
        this.name = name;
        this.price = price;
        this.restoran = restoran;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }



    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
