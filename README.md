# E-Commerce Product Service

## General Description

This repository contains the source code for the **Product Service**, a microservice responsible for managing all product-related information within a larger E-Commerce platform.

This service is the first step in the **gradual migration of a monolithic application to a microservices architecture**. It is built following the principles of **Clean Architecture**, also known as Hexagonal Architecture or Ports and Adapters.

The goal of this project is to demonstrate the application of modern design patterns, decoupled architectures, containerization, and CI/CD practices in a backend context.

## Main Features

*   **Product CRUD Management:**
    *   Retrieve all products.
    *   Retrieve a product by its ID.
    *   Create a new product.
    *   Update an existing product.
    *   Delete a product.
*   **RESTful API:** Exposes clear and standard endpoints to interact with products.
*   **Data Validation:** Implements validations on incoming requests.
*   **Exception Handling:** Provides consistent error responses.
*   **Persistence:** Stores product data in a relational database (PostgreSQL in the Docker environment).

## Architecture

This microservice follows the principles of **Clean Architecture**:

*   **Domain Layer (`domain`):** Contains the core business logic and pure models (POJOs) such as `Product`. It defines the interfaces (Ports) for communication with the outside world (`input.ProductService`, `output.ProductRepository`). It is independent of frameworks and infrastructure details.
*   **Application Layer (`application`):** Orchestrates use cases (`usecases.ProductServiceImpl`) by implementing the domain's input ports. It depends solely on the Domain layer.
*   **Infrastructure Layer (`infrastructure`):** Contains technical details and communication with the outside world (Adapters). It implements the domain's output ports. It includes:
    *   **Configuration (`config`):** General configuration, security (`security`), and exception handling (`exception`).
    *   **Persistence (`persistence`):** JPA adapter (`adapter.ProductRepositoryAdapter`) that implements `ProductRepository`, JPA entities (`entity.ProductEntity`), and Spring Data repositories (`repository.JpaProductRepository`).
    *   **REST API (`rest`):** Spring Boot controller (`controllers.ProductController`), DTOs (`dto`), and Mappers (`mapper`).

**Typical Flow (e.g., Create Product):**  
`HTTP Request` -> `ProductController` -> `ProductMapper` (DTO to Domain) -> `ProductServiceImpl` -> `ProductRepository` (Interface) -> `ProductRepositoryAdapter` -> `JpaProductRepository` (Spring Data) -> `ProductEntity` -> Database

## Technologies Used

*   **Language:** Java 21
*   **Framework:** Spring Boot 3.4.4
*   **Spring Modules:** Spring Web, Spring Data JPA, Spring Validation, Spring Security (basic)
*   **Persistence:** Hibernate, PostgreSQL (in Docker), H2 (for some tests)
*   **Build:** Maven
*   **Testing:** JUnit 5, Mockito, Spring Boot Test
*   **Containerization:** Docker, Docker Compose
*   **CI/CD:** GitHub Actions
*   **Others:** Lombok
