package com.example.REST_API_DELIVERY.service;

import com.example.REST_API_DELIVERY.exception.BadRequestException;
import com.example.REST_API_DELIVERY.exception.ResourceNotFoundException;
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
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Пользователь с таким айди не найден" + userId));
        return cartRepository.findByUserAndActiveTrue(user).orElseGet(() -> {
            Cart c = new Cart();
            c.setUser(user);
            return cartRepository.save(c);
        });
    }

    public Cart addItem(Long userId, Long dishId, int amount) {
        if (amount <= 0) {
            throw new BadRequestException("Количество должно быть больше 0");
        }
        Cart cart = getCart(userId);
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("Не найдено" + dishId));
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setDish(dish);
        item.setAmount(amount);
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
        CartItem item = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("Элемент с id " + cartItemId + " не найден"));
        cartItemRepository.deleteById(cartItemId);
    }

    public CartItem updateQuantity(Long cartItemId, int amount) {
        CartItem item = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ResourceNotFoundException("не найдено"));
        item.setAmount(amount);
        return cartItemRepository.save(item);
    }
}
