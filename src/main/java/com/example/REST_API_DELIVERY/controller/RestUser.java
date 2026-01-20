package com.example.REST_API_DELIVERY.controller;

import com.example.REST_API_DELIVERY.model.Role;
import com.example.REST_API_DELIVERY.model.User;
import com.example.REST_API_DELIVERY.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class RestUser {
   private UserService userService;
   public RestUser(UserService userService){
       this.userService = userService;
   }
    //запрос на регистрацию
   @PostMapping
    public ResponseEntity<User> registration(@RequestBody User user){
       User savedUser = userService.registration(user.getUsername(), user.getPassword(),user.getRole());
       return ResponseEntity.ok(savedUser);
   }
    //получаем айди
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id,@RequestBody User user){
       User gerUserById = userService.getUserById(id);
       return ResponseEntity.ok(user);
    }
    //обновляем
    @PutMapping("/{id}")
    public ResponseEntity<User> updatedUser(@PathVariable Long id,@RequestBody User user){
       User updatedUser = userService.updateUser(id, user.getUsername(), user.getPassword(), user.getRole());
       return ResponseEntity.ok(updatedUser);
    }
    //поиск по роям
    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) Role role){
       if (role == null){
           return ResponseEntity.ok(userService.getAllUser());
       }
       return ResponseEntity.ok(userService.getUserByRole(role));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        User user = userService.deleteUser(id);
        return ResponseEntity.ok(user);

    }

}
