# project_flight_management
A Spring Boot REST API for managing airline flight inventory with JWT-based authentication and role-based authorization.
Features

JWT Authentication: Secure token-based authentication
Role-Based Authorization: Administrator and User roles with different privileges
Flight Management: CRUD operations for flight inventory
In-Memory Database: H2 database for easy testing and development
Input Validation: Comprehensive validation for IATA codes and flight data

Technology Stack

Spring Boot 3.5.7
Spring Security with JWT
Spring Data JPA
H2 Database
Maven
Java 17
Lombok

Project Structure
src/main/java/comcom/airxelerate/management_flight_api
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/airxelerate/flight_managment/
│   │   │       ├── config/
│   │   │       ├── controllers/
│   │   │       ├── dtos/
│   │   │       ├── entities/
│   │   │       ├── enums/
│   │   │       ├── Exceptions/
│   │   │       ├── repositories/
│   │   │       ├── security/
│   │   │       ├── services/
│   │   │       │   ├── impl/
│   │   │       └── ui/
│   │   └── resources/
│   └── test/
├── docs/
└── README.md
Getting Started
Prerequisites

Java 17 or higher
Maven 3.6 or higher

Installation

Clone the repository or extract the files
Navigate to the project directory
Build the project:
bash   mvn clean install
Run the application:
bash   mvn spring-boot:run
The application will start on http://localhost:8080
Default Users
==>USER
Username: user
Password: 2025
==> ADMINISTRATOR:
Username: admin
Password: 2025
API Documentation
The REST API documentation is available at /swagger-ui.html when running the application.
