package com.example.REST_API_DELIVERY.model;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table (name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    @ManyToOne
    private Restoran restoran;

    private boolean active = true;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
