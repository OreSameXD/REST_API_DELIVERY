package com.example.REST_API_DELIVERY.controller;

import com.example.REST_API_DELIVERY.model.Cart;
import com.example.REST_API_DELIVERY.model.CartItem;
import com.example.REST_API_DELIVERY.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }
    @PostMapping("/items")
    public Cart addItem(@RequestParam Long userId, @RequestParam Long dishId, @RequestParam(defaultValue = "1") int amount) {
        return cartService.addItem(userId, dishId, amount);
    }
    @DeleteMapping("/items{id}")
    public String removeItem(@PathVariable Long id){
        cartService.removeItem(id);
        return "Удалили";
    }
    @PutMapping("/items/{id}")
    public CartItem updateQuantity(@PathVariable Long id, @RequestParam int amount) {
        return cartService.updateQuantity(id, amount);
    }
    @GetMapping
    public Cart getCart(@RequestParam Long userId){
        return cartService.getCart(userId);
    }
    @DeleteMapping
    public String clearCart(@RequestParam Long userId){
        cartService.clearCart(userId);
        return "корзина очищена";
    }
}