
package com.fooddelivery.onlinefooddelivery.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.onlinefooddelivery.model.Address;
import com.fooddelivery.onlinefooddelivery.model.User;
import com.fooddelivery.onlinefooddelivery.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(
            UserService userService) {

        this.userService = userService;
    }


    @PostMapping
    public User createUser(
            @Valid @RequestBody User user) {

        return userService.createUser(
                user
        );
    }



    @GetMapping
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }



    @GetMapping("/{id}")
    public User getUserById(
            @PathVariable String id) {

        return userService.getUserById(
                id
        );
    }



    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable String id,
            @Valid @RequestBody User user) {

        return userService.updateUser(
                id,
                user
        );
    }



    @PutMapping("/{id}/address")
    public User updateAddress(
            @PathVariable String id,
            @RequestBody Address address) {

        return userService.updateAddress(
                id,
                address
        );
    }



    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable String id) {

        userService.deleteUser(
                id
        );
    }

}