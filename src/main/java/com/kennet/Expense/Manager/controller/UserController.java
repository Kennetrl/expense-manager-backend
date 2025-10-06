package com.kennet.Expense.Manager.controller;

import com.kennet.Expense.Manager.model.User;
import com.kennet.Expense.Manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
/**
 * REST endpoints for managing users.
 */
public class UserController {

    @Autowired
    private UserService userService;

    //Listar usuarios
    @GetMapping
    /**
     * Lists all users.
     */
    public List<User> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    /**
     * Retrieves a user by id.
     */
    public ResponseEntity<User> getUserBydId(@PathVariable Long id){
        return userService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    /**
     * Creates a new user.
     */
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/{id}")
    /**
     * Updates an existing user (note: uses POST rather than PUT/PATCH).
     */
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        return userService.getUserById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPasswordHash(userDetails.getPasswordHash());
            return ResponseEntity.ok(userService.saveUser(user));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    /**
     * Deletes a user by id.
     */
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if (userService.getUserById(id).isPresent()){
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
