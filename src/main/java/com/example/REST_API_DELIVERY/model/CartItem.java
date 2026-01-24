package com.example.REST_API_DELIVERY.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Dish dish;

    private int quantity = 1;


    public Long getId() {
        return id;
    }
    public Cart getCart() {
        return cart;
    }
    public Dish getDish() {
        return dish;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public void setDish(Dish dish) {
        this.dish = dish;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
