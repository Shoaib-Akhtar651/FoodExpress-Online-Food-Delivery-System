# FoodExpress – Online Food Delivery System

FoodExpress is a full-stack online food delivery system developed using Spring Boot, MongoDB, HTML, CSS, and JavaScript.

The application allows users to register and log in securely, browse restaurants and food items, manage their cart, place orders, manage delivery addresses, make payments, view order history, and cancel orders when required.

## Features

- User registration and login
- JWT-based authentication and authorization
- Restaurant listing and food browsing
- Food search functionality
- Add food items to cart
- Update cart quantities
- Remove items from cart
- Order placement
- Order history
- Delivery address management
- Order cancellation with cancellation reasons
- Payment workflow
- Food availability management
- RESTful APIs
- Swagger/OpenAPI API documentation

## Technology Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT
- REST APIs
- MongoDB

### Frontend
- HTML5
- CSS3
- JavaScript

### Tools
- Spring Tools for Eclipse
- MongoDB
- Git
- GitHub
- Postman
- Swagger/OpenAPI

## Project Structure

```text
FoodExpress-Online-Food-Delivery-System
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.fooddelivery.onlinefooddelivery
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── exception
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       ├── security
│   │   │       └── service
│   │   │
│   │   └── resources
│   │       ├── static
│   │       │   ├── css
│   │       │   ├── images
│   │       │   ├── index.html
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   ├── dashboard.html
│   │       │   ├── cart.html
│   │       │   ├── order.html
│   │       │   └── payment.html
│   │       └── application.properties
│   │
│   └── test
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
