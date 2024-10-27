# HotelHub
<br>
<p align="center" margin="auto">
    <kbd>
<img align="center" 
            src="https://github.com/KhaledAshrafH/HotelHub/blob/main/hotelhub_cover.png"
            alt="KhaledAshrafH"  height="280" style="border-radius: 20px;"/>
    </kbd>
  </p>
 <h1 align="center"></h1>

HotelHub is an advanced hotel booking management system designed to facilitate seamless booking of accommodations and event spaces. This web application aims to simplify the hotel management process by providing distinct functionalities for various user roles including Guests, Admins, and Staff. The system is built with a focus on security, usability, and scalability, making it suitable for both small boutique hotels and large hotel chains.

 
## Features

- **User Roles:** The system understands different roles and grants specific functionalities tailored to Guests, Admins, and Staff. Guests can book rooms and view their reservations, Admins can oversee and     manage the entire system, while Staff can handle daily operations and customer inquiries.
- **Booking Management:** Allows guests to make, view, modify, and cancel room and event space bookings seamlessly. It provides calendar views of availability and has a user-friendly interface to streamline the reservation process.
- **Notification System:** The application keeps users informed by notifying them about booking status changes, promotional offers, and other important updates through real-time notifications.
- **User Management:** Admins have the ability to manage user accounts, roles, and permissions to ensure appropriate access to different functionalities. This helps in maintaining data integrity and security.
- **Robust Security:** Integrated with Spring Security, employing JSON Web Tokens (JWT) for safe and secure authentication, ensuring that user data is protected.
- **Database Management:** Efficient use of MySQL for relational data management, ensuring reliable storage, retrieval, and integrity of data relating to bookings, user accounts, and hotel resources.
- **API Documentation:** Automatically generated API documentation using SpringDoc OpenAPI, allowing developers to easily understand and integrate with the backend services.
- **Email Notifications:** Configured to send automated email notifications for booking confirmations, reminders, and updates, enhancing the user experience by keeping them informed.

### Possible Improvements

- **Multi-Language Support** 
- **Advanced Analytics Dashboard for Admins** 
- **Chatbot for Customer Support** 
- **Loyalty Program Integration** 
- **Integration with Third-Party Services** 
- **Automated Backup System**  

## User Roles Overview
1. **Guests:**
   - Create and manage their profiles
   - Search for available accommodations 
   - Make online reservations
   - Receive booking confirmations via email
   - Cancel or modify existing bookings
  
2. **Admins:**
   - Oversee the entire hotel management system
   - Manage user accounts and their roles
   - Configure hotel policies, rates, and promotional offers
   - Generate reports on bookings, revenues, and user activities

3. **Staff:**
   - Access booking details for operational purposes
   - Handle inquiries and support requests
   - Assist guests with check-ins and check-outs
   - Update room availability status

     
## Technologies Used

- **Backend:** Spring Boot, Spring Data JPA
- **Database:** MySQL
- **Security:** Spring Security, JWT
- **Validation:** Spring Boot Validation
- **Email Services:** Spring Boot Starter Mail
- **Testing:** Spring Test
- **Mapping:** MapStruct
- **Documentation:** SpringDoc OpenAPI Starter

## Architectural Overview

The project follows a clean architecture model featuring multiple layers, ensuring a well-organized codebase and separation of concerns:

- **Controller Layer:** Responsible for handling incoming HTTP requests and delegating them to the appropriate service layer for processing.
- **Service Layer:** Encapsulates the business logic, managing data transfers between the controller and the repository layer, and performing necessary validations.
- **Repository Layer:** Responsible for interacting with the MySQL database through Spring Data JPA, performing CRUD operations on entities.
- **Model Layer:** Contains the data structures, including both DTOs and entities, that the application will use throughout its execution.


### Project Structure
```
      HotelHub
      ├── src
      │   ├── main
      │   │   ├── java
      │   │   │   └── hotelhub 
      │   │   │           ├── config <-- Configuration files
      │   │   │           ├── controller <--  RESTful controllers
      │   │   │           ├── exception <-- Custom exception classes
      │   │   │           ├── filter  <-- JWT filtering classes for security
      │   │   │           ├── model <-- Core data models
      │   │   │           │   ├── dto <-- Data transfer objects (DTOs)
      │   │   │           │   ├── entity <-- Entites of project's database
      │   │   │           │   ├── enums <-- Enumeration classes for various data types
      │   │   │           │   └── mapper <-- Mappers for converting between entities and DTOs
      │   │   │           ├── repository <-- Data access layer interfaces
      │   │   │           ├── service <-- Business logic layer
      │   │   │           └── util <-- Utility classes
      │   │   └── resources
      │   │       ├── application.properties <-- Configuration properties
      │   │       └── static
      │   └── test
      ├── .gitignore
      ├── pom.xml
      └── README.md
 ```

## Installation

### Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven 3.6.0 or higher
- MySQL Server

### Steps

1. **Clone the Repository**
 ```bash
   git clone https://github.com/KhaledAshrafH/HotelHub.git
   cd HotelHub
```

2. Set Up Database
Create a MySQL database named hotelhub and update the application.properties file with your database credentials:

```
spring.datasource.url=jdbc:mysql://localhost:3306/hotelhub
spring.datasource.username=root
spring.datasource.password=
```

3. Build the Project
Use Maven to build the project:
```
mvn clean install
```

4. Run the Application
Start the application using:
```
mvn spring-boot:run
```

The application starts on
```
http://localhost:8009
```

## API Documentation
Access the API documentation at [http://localhost:8009/api/docs](http://localhost:8009/api/docs) to explore all available endpoints, their methods, and the required parameters, allowing for easy integration and testing.


## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments
Special thanks to all contributors and open-source libraries that made this project possible. Your support and dedication do not go unnoticed. Happy coding!

