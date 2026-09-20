package com.fooddelivery.onlinefooddelivery.dto;

public class AuthResponse {

    private String message;
    private String token;
    private String userId;
    private String name;
    private String email;

    public AuthResponse(
            String message,
            String token,
            String userId,
            String name,
            String email) {

        this.message = message;
        this.token = token;
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}