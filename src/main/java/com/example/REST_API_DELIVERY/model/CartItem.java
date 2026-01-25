package com.example.REST_API_DELIVERY.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;



@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ManyToOne

    private Dish dish;

    private int amount = 1;


    public Long getId() {
        return id;
    }
    public Cart getCart() {
        return cart;
    }
    public Dish getDish() {
        return dish;
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


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
