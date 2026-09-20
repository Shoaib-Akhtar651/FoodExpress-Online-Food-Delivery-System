
package com.fooddelivery.onlinefooddelivery.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fooddelivery.onlinefooddelivery.exception.ResourceNotFoundException;
import com.fooddelivery.onlinefooddelivery.model.Address;
import com.fooddelivery.onlinefooddelivery.model.User;
import com.fooddelivery.onlinefooddelivery.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {

            throw new RuntimeException(
                    "User already exists with email: "
                            + user.getEmail()
            );
        }


        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );


        return userRepository.save(user);
    }


    public List<User> getAllUsers() {

        return userRepository.findAll();
    }


    public User getUserById(String id) {

        return userRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        )
                );
    }


    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: "
                                        + email
                        )
                );
    }


    public User updateUser(
            String id,
            User user) {

        User existingUser =
                userRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found with id: "
                                                + id
                                )
                        );


        existingUser.setName(
                user.getName()
        );


        existingUser.setEmail(
                user.getEmail()
        );


        if (user.getPassword() != null
                && !user.getPassword().isBlank()) {

            existingUser.setPassword(
                    passwordEncoder.encode(
                            user.getPassword()
                    )
            );
        }


        existingUser.setPhone(
                user.getPhone()
        );


        existingUser.setAddress(
                user.getAddress()
        );


        return userRepository.save(
                existingUser
        );
    }


    public User updateAddress(
            String id,
            Address address) {

        User existingUser =
                userRepository.findById(id)

                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User not found with id: "
                                                + id
                                )
                        );


        existingUser.setAddress(
                address
        );


        return userRepository.save(
                existingUser
        );
    }



    public void deleteUser(String id) {

        if (!userRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "User not found with id: " + id
            );
        }


        userRepository.deleteById(id);
    }

}