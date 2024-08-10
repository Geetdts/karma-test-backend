# karma-test-backend

Backend application for Karma Technologies test using Spring Boot.

## Test Question

### Task: Simple Angular Application with the Following Features:

#### Login Screen:
- Basic login functionality with form validation and error handling.
- Appropriate styling to ensure a user-friendly interface.

#### List Management Screen:
- Ability to view a list of items.
- Functionality to add, edit, and delete items.
- Form validation and error handling for these operations.
- Basic styling for a clean and functional user interface.

#### Backend:
The backend for this application will be a Spring Boot application. You can use static data for API responses.



## Introduction
The `karma-test-backend` application serves as a backend service for managing sales and users. It provides RESTful API endpoints to handle operations such as user registration, login, and sales management.

## Features
- User registration and authentication
- CRUD operations for sales
- Data validation and error handling
- Swagger API documentation for easy testing

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- ModelMapper
- Swagger UI for API documentation

## Getting Started
To get a local copy of the project up and running, follow these steps:

### Prerequisites
- Java 17 or higher
- Maven
- MySQL (or your preferred database)

### Clone the Repository
```bash
git clone https://github.com/your-username/karma-test-backend.git
cd karma-test-backend
```
## API Endpoints

Here are some of the key API endpoints available in the application:

### User Endpoints
- **POST** `/api/v1/user` - Register a new user
- **POST** `/api/v1/user/login` - Login a user

### Sale Endpoints
- **POST** `/api/v1/sale` - Create a new sale
- **GET** `/api/v1/sale` - Get all sales
- **PUT** `/api/v1/sale` - Update an existing sale
- **DELETE** `/api/v1/sale/{id}` - Delete a sale by ID

## Documentation
Access the Swagger UI at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to explore the API documentation and test endpoints.

