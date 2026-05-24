# Customer Management System

A full-stack Java customer management system consisting of:

- Spring Boot REST API backend
- JavaFX desktop client

The desktop application communicates with the backend using REST APIs and JSON.

---

# Project Structure

customer-management-system/
│
├── backend/
│   Spring Boot REST API
│
├── desktop-client/
│   JavaFX Desktop Application
│
├── database/
│   SQL database creation script
│
└── README.md

---

# Technologies Used

## Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL

## Desktop Client
- JavaFX
- OkHttp
- Jackson

---

# Backend Setup

## 1. Open backend project

Open the `backend` folder in IntelliJ IDEA.

## 2. Configure database

Update:

src/main/resources/application.properties

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/customer_management
spring.datasource.username=root
spring.datasource.password=yourpassword
```

## 3. Run SQL Script

Execute:

database/customer_management.sql

inside MySQL.

## 4. Start Spring Boot backend

Run:

```bash
mvn spring-boot:run
```

Backend runs on:

http://localhost:8080

---

# REST Endpoints

| Method | Endpoint |
|---|---|
| GET | /api/v1/customers |
| GET | /api/v1/customers/{id} |
| POST | /api/v1/customers |
| PUT | /api/v1/customers/{id} |
| DELETE | /api/v1/customers/{id} |

---

# Desktop Client Setup

## 1. Open desktop-client project

Open the `desktop-client` folder in IntelliJ IDEA.

## 2. Install JavaFX SDK

Download JavaFX SDK:

https://openjfx.io/

## 3. Configure VM Options

Example:

```text
--module-path "C:\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml
```

## 4. Start Desktop App

Run:

MainApp.java

---

# Communication Between Desktop App and Backend

The JavaFX desktop application communicates with the Spring Boot backend using:

- REST APIs
- HTTP requests using OkHttp
- JSON serialization/deserialization using Jackson

Example flow:

JavaFX App
→ OkHttp HTTP Request
→ Spring Boot REST API
→ JSON Response
→ JavaFX TableView Update

---

# Features

- Display all customers
- Add customer
- Update customer
- Delete customer
- Async API communication
- Error handling
- JSON parsing
- MySQL persistence

---

# Author

Your Name
