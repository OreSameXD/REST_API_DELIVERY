package com.example.REST_API_DELIVERY.service;

import com.example.REST_API_DELIVERY.model.Cart;
import com.example.REST_API_DELIVERY.model.CartItem;
import com.example.REST_API_DELIVERY.model.Dish;
import com.example.REST_API_DELIVERY.model.User;
import com.example.REST_API_DELIVERY.repository.CartItemRepository;
import com.example.REST_API_DELIVERY.repository.CartRepository;
import com.example.REST_API_DELIVERY.repository.DishRepository;
import com.example.REST_API_DELIVERY.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private DishRepository dishRepository;

    public CartService (CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository, DishRepository dishRepository){
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
    }
    public Cart getCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return cartRepository.findByUserAndActiveTrue(user).orElseGet(() -> {
            Cart c = new Cart();
            c.setUser(user);
            return cartRepository.save(c);
        });
    }

    public Cart addItem(Long userId, Long dishId, int quantity) {
        Cart cart = getCart(userId);
        Dish dish = dishRepository.findById(dishId).orElseThrow();
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setDish(dish);
        item.setQuantity(quantity);
        cartItemRepository.save(item);
        cart.getItems().add(item);
        return cartRepository.save(cart);
    }

    public void clearCart(Long userId) {
        Cart cart = getCart(userId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
    public void removeItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public CartItem updateQuantity(Long cartItemId, int quantity) {
        CartItem item = cartItemRepository.findById(cartItemId).orElseThrow();
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }
}
