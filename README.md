# Karya
A Spring Boot project to manage users, vendors, and vehicles.

[![Demo Video](https://github.com/user-attachments/assets/9b88a120-72c6-4079-8060-fc449800ec6b)]([https://www.youtube.com/watch?v=wV-E4ZyQujM](https://youtu.be/5lG09kaNb30))


## About

Karya implements the following:

- Registering a New User
- User Login
- Vehicle Registration and Management
- Assigning Sub Vendors
- User Management

-----

### API Endpoints

| Endpoint                                 | Method | Usage                                      |
|------------------------------------------|--------|---------------------------------------------|
| `/auth/register`                         | POST   | Register a new user                         |
| `/auth/login`                            | POST   | User login                                  |
| `/auth/assign-subvendor`                 | POST   | Assign a sub-vendor to a main vendor        |
| `/auth/subvendors/{vendorId}`            | GET    | Get all sub-vendors for a vendor            |
| `/vehicles/vendor/{vendorId}`            | POST   | Add a vehicle for a specific vendor         |
| `/vehicles/vendor/{vendorId}`            | GET    | Get all vehicles for a specific vendor      |


### Folder Structure

```bash
karya/
├── .gitattributes
├── .gitignore
├── LICENSE
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
├── .vscode/
│   └── settings.json
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   ├── main/
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── schema.sql
│   │   └── java/
│   │       └── com/
│   │           └── akarsh/
│   │               ├── karya/
│   │               │   └── KaryaApplication.java
│   │               └── auth/
│   │                   ├── service/
│   │                   │   ├── UserService.java
│   │                   │   └── VehicleService.java
│   │                   ├── repository/
│   │                   │   ├── UserRepository.java
│   │                   │   ├── VehicleRepository.java
│   │                   │   └── VendorRepository.java
│   │                   ├── entity/
│   │                   │   ├── User.java
│   │                   │   ├── Vehicle.java
│   │                   │   └── Vendor.java
│   │                   ├── dto/
│   │                   │   ├── AuthResponse.java
│   │                   │   ├── LoginDto.java
│   │                   │   ├── SubVendorAssignmentDto.java
│   │                   │   ├── UserDto.java
│   │                   │   ├── UserRegistrationDto.java
│   │                   │   └── VehicleDto.java
│   │                   ├── controller/
│   │                   │   ├── UserController.java
│   │                   │   └── VehicleController.java
│   │                   └── config/
│   │                       └── SecurityConfig.java
│   └── test/
│       └── java/
│           └── com/
│               └── akarsh/
│                   └── karya/
│                       └── KaryaApplicationTests.java
```





### Key takeaways
Learnt a lot by implementing it, deadline felt very tight, spent 2.5 hours fixing the first issue. Thank you for the opportunity, and thank you for reading! 
Have a nice day.
