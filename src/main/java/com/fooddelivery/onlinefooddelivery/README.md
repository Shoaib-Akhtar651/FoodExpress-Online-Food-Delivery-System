# Online Food Delivery System

A backend REST API for an Online Food Delivery System built using Spring Boot, MongoDB, Spring Security and JWT Authentication.

## Features

- User Registration and Login
- JWT based Authentication
- Password Encryption using BCrypt
- User Management
- Restaurant Management
- Category Management
- Food Management
- Cart Management
- Order Management
- Payment Management
- Input Validation
- Global Exception Handling
- RESTful APIs
- Swagger / OpenAPI API Documentation
- MongoDB Database Integration

## Technologies Used

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data MongoDB
- Spring Security
- JWT
- BCrypt Password Encoder
- Jakarta Validation
- Swagger / OpenAPI
- Maven
- MongoDB

## Project Structure

```text
src/main/java/com/fooddelivery/onlinefooddelivery

├── config
│   ├── SecurityConfig.java
│   └── OpenAPIConfig.java
│
├── controller
│   ├── AuthController.java
│   ├── UserController.java
│   ├── RestaurantController.java
│   ├── CategoryController.java
│   ├── FoodController.java
│   ├── CartController.java
│   ├── OrderController.java
│   └── PaymentController.java
│
├── dto
│   ├── AuthResponse.java
│   └── LoginRequest.java
│
├── exception
│   ├── ResourceNotFoundException.java
│   └── GlobalExceptionHandler.java
│
├── model
│   ├── Address.java
│   ├── User.java
│   ├── Restaurant.java
│   ├── Category.java
│   ├── Food.java
│   ├── Cart.java
│   ├── CartItem.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── Payment.java
│
├── repository
│   ├── UserRepository.java
│   ├── RestaurantRepository.java
│   ├── CategoryRepository.java
│   ├── FoodRepository.java
│   ├── CartRepository.java
│   ├── OrderRepository.java
│   └── PaymentRepository.java
│
├── security
│   ├── JwtService.java
│   └── JwtAuthenticationFilter.java
│
└── service
    ├── AuthService.java
    ├── UserService.java
    ├── RestaurantService.java
    ├── CategoryService.java
    ├── FoodService.java
    ├── CartService.java
    ├── OrderService.java
    └── PaymentService.java