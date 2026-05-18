 HireBridge Job Portal

This project is a full-stack Job Portal application developed using Java Spring Boot and PostgreSQL that allows users to browse jobs, apply for jobs, upload resumes, and receive email notifications.

## Project Structure

- **Controller Layer:** Handles REST API requests and responses.
- **Service Layer:** Contains business logic such as email handling and file upload.
- **Repository Layer:** Manages database operations using JPA.
- **Database:** PostgreSQL for storing jobs, users, and applications.

## Key Features

- User Authentication with OTP
- Job Search and Application
- Resume Upload Functionality
- Email Notifications using SMTP
- RESTful APIs

## Technologies Used

- Java
- Spring Boot
- PostgreSQL
- Spring Data JPA
- Hibernate
- Maven

## Getting Started

1. Configure PostgreSQL credentials in `application.properties`
2. Run the project using:

```bash
mvn spring-boot:run
