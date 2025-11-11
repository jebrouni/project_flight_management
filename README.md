 Flight Management System

A Spring Boot–based backend application for managing flight inventory with **JWT authentication**, **role-based authorization**, and **H2 In-Memory database** for fast development and testing.

---

 Features

- **JWT Authentication** – Secure token-based authentication and authorization  
- **Role-Based Access Control** – Separate privileges for **Admin** and **User** roles  
- **Flight Management** – CRUD operations for managing flight inventory  
- **In-Memory Database (H2)** – Simple and fast testing environment  
- **Input Validation** – Strong validation for IATA codes and flight data  
- **REST API Documentation** – Auto-generated via Swagger UI  

---

## 🧰 Technology Stack

| Layer | Technologies |
|-------|---------------|
| Language | **Java 17** |
| Framework | **Spring Boot 3.5.7** |
| Security | **Spring Security with JWT** |
| Data Access | **Spring Data JPA** |
| Database | **H2  Database** |
| Build Tool | **Maven 3.8+** |
| Utilities | **Lombok** |

---

Project Structure

flight-management-system/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/airxelerate/flight_management/
│ │ │ ├── config/
│ │ │ ├── controllers/
│ │ │ ├── dtos/
│ │ │ ├── entities/
│ │ │ ├── enums/
│ │ │ ├── exceptions/
│ │ │ ├── repositories/
│ │ │ ├── security/
│ │ │ ├── services/
│ │ │ │ └── impl/
│ │ │ 
│ │ └── resources/
│ └── test/
├── 
└── README.md



---

Prerequisites

Make sure you have the following installed:

- **JDK 17**
- **Maven 3.8+**


---

##  Setup and Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/jebrouni/project_flight_management.git
   cd project_flight_management
2. Build the project
mvn clean install

3.Run the application
mvn spring-boot:run


Access the application
API base URL: http://localhost:8080/api/flight

Swagger UI: http://localhost:8080/swagger-ui.html

Default Users
===> for User :
username : user
password : 2025
===> for Admin:
username : admin
password : 2025

== Admin users can add, or delete flights.
==Regular users can only view flight data.

 API Documentation
Once the application is running, open your browser and go to:
http://localhost:8080/swagger-ui.html

You can explore all available endpoints there.

Testing
Run the tests with Maven:

mvn test
This will execute all unit and integration tests.

 Contributing
 
Fork the repository
Create a new feature branch
git checkout -b feature/your-feature-name
Commit your changes
git commit -m "Add your feature"
Push to your branch

git push origin feature/your-feature-name
Create a Pull Request on GitHub 🎉

📝 License
This project is open-source and available under the MIT License.

Author: Soufian Eljebrouni
Version: 1.0.0
Date: 2025
