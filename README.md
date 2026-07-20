# Library Management API

A REST API for managing books, authors and members.

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Swagger / OpenAPI

## Architecture

The project follows a layered architecture:

Controller → Service → Repository

DTOs are used to prevent returning entities directly from the API.

## Features

- Author CRUD operations
- Book CRUD operations
- Member CRUD operations
- Input validation
- Global exception handling
- Pagination
- Sorting
- Swagger API documentation
- Service layer tests