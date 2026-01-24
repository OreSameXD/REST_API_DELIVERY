package com.example.REST_API_DELIVERY.service;

import com.example.REST_API_DELIVERY.exception.BadRequestException;
import com.example.REST_API_DELIVERY.exception.ResourceNotFoundException;
import com.example.REST_API_DELIVERY.model.Role;
import com.example.REST_API_DELIVERY.model.User;
import com.example.REST_API_DELIVERY.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User registration(String username, String password, Role role){
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null){
            throw new RuntimeException("Пользователь уже есть");
        }
        User user = new User(username,password,role);
        return userRepository.save(user);
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("пользователь не найден"));
    }
    public User updateUser(Long id, String username,String password,Role role){
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Пользователь" + id + "не найден"));
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new BadRequestException("Имя и пароль не могут быть пустыми");
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        return  userRepository.save(user);
    }
    public List<User> getUserByRole(Role role){

        return userRepository.findByRole(role);
    }

    public User deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Пользователь" + id + "не найден"));
        user.setActive(false);
        return userRepository.save(user);
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

}
