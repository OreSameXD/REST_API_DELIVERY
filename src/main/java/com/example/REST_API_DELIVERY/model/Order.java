package com.example.REST_API_DELIVERY.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    Restoran restoran;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.New;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
