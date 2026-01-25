package com.example.REST_API_DELIVERY.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    private int amount;

    public OrderItem(){

    }
    public OrderItem(Order order, Dish dish , int amount){
        this.order = order;
        this.dish = dish;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Dish getDish() {
        return dish;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
