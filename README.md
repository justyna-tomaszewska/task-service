# task-service: High-Performance Task Manager Backend

[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue)](https://kotlinlang.org/)
[![Spring Boot](https://img.shields.io/badge/Framework-Spring%20Boot-brightgreen)](https://spring.io/projects/spring-boot)
[![Cache](https://img.shields.io/badge/Cache-Hazelcast-orange)](https://hazelcast.com/)
[![License](https://img.shields.io/badge/License-MIT-lightgray)](LICENSE)

## 🌟 Project Goal

This repository contains a **RESTful API** for a microservice-based Task Management application.

This project serves as a comprehensive development goal to achieve mastery in **Kotlin backend development**, focusing
on building a robust, high-performance, and resilient API using modern techniques like distributed caching and idiomatic
Spring integration.

## 🚀 Key Technologies

The project specifically focuses on integrating and mastering the following core backend concepts:

* **Language:** **Kotlin** (Idiomatic syntax, leveraging its powerful features).
* **Framework:** **Spring Boot 3** (Rapid application development and configuration).
* **Request Handling:** **Spring Web** (Using **REST Controllers** and understanding the underlying **Servlet API**
  model).
* **Persistence:** **Spring Data JPA** (Efficient data modeling and interaction with a relational database).
* **Distributed Caching:** **Hazelcast** integration for optimizing read performance and demonstrating **Cache-Aside**
  and **Read-Through** strategies.
* **Architecture:** Clean, layered architecture suitable for microservices.
* **Security:** Basic user authentication and authorization.

## ⚙️ Setup and Run Locally

### Prerequisites

* Java Development Kit (JDK) 17+
* Gradle (or Maven, if converted)
* A REST client (e.g., Postman, Insomnia)

### Steps

1. **Build the Project:**
   ```bash
   ./gradlew clean build
   ```
   1.1
   ```bash
   ./gradlew clean build --refresh-dependencies
    ```   
   
    1.2
   ```bash
   ./gradlew dependencies --configuration runtimeClasspath

    ```

2. **Run the Application:**
   The application is configured to use an in-memory **H2 database** by default for simplicity, making setup
   instantaneous.
   ```bash
   ./gradlew bootRun
   ```
   The API will start on `http://localhost:8080`.

## 📌 Core API Endpoints

All endpoints are protected and require a valid authentication token after user registration.

| Functionality | HTTP Method | Endpoint | Description |
| :--- | :--- | :--- | :--- |
| **Authentication** | `POST` | `/api/auth/register` | Create a new user account. |
| **Task Creation** | `POST` | `/api/tasks` | Create a new task. |
| **Get All Tasks** | `GET` | `/api/tasks` | Retrieve all tasks for the authenticated user. **(Filterable by status)** |
| **Get Single Task** | `GET` | `/api/tasks/{id}` | Retrieve a specific task. **(Utilizes Hazelcast Cache)** |
| **Update Task** | `PUT` | `/api/tasks/{id}` | Update task details or status. |

## 💡 Caching Strategy

The application uses **Hazelcast** as its primary caching layer to demonstrate performance gains for frequently accessed
data.

* **Single Task Retrieval (`GET /api/tasks/{id}`):** Implements a **Cache-Aside** strategy to minimize database lookups
  for individual tasks.
* **Configuration:** Hazelcast is configured via Spring's `@EnableCaching` using an in-process, embedded configuration
  for development.

## 🧑‍💻 Development Notes

This project focused heavily on:

1. Writing **idiomatic Kotlin** over Java-style code.
2. Properly handling exceptions using **`@ControllerAdvice`** for clean API error responses.
3. Implementing robust **input validation** on DTOs.
4. Ensuring **unit and integration test coverage** for critical business logic.