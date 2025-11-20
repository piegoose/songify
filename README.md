# Songify

A full-stack web application for managing a songs catalog, built with Java 17 and Spring Boot.

## Features
- Manage songs: create, view, edit, delete entries (CRUD)  
- Validated user input using `@NotNull`, `@Size`, etc.  
- Relational database persistence via JPA/Hibernate  
- Web interface using Spring MVC + Thymeleaf  
- Docker-Compose setup for easy local environment (DB + app)  
- Clean, layered architecture: Controller → Service → Repository  

## Tech stack
- Java 17  
- Spring Boot (Spring MVC, Spring Data JPA)  
- Hibernate / JPA  
- H2 / MySQL  
- Maven build system  
- Docker & Docker-Compose  
- HTML + Thymeleaf frontend  
- Git / GitHub  

## How to run locally
1. Clone the repo  
2. `docker-compose up` (starts the database + app)  
3. Visit `http://localhost:8080` in your browser  
4. Use the web UI to add/edit/delete songs  

## What I learned
- How to design a layered backend architecture and enforce separation of concerns  
- How to integrate Spring Data JPA for database operations  
- How to validate input and handle edge cases in a web app  
- How to containerise an application using Docker / Docker-Compose  
- How to build a web UI using Spring MVC + Thymeleaf  

## Future improvements
- Add REST API endpoints only (for headless frontend)  
- Add unit/integration tests (JUnit + Mockito)  
- Add authentication (Spring Security)  
- Migrate to PostgreSQL for production  
- Add CI/CD pipeline  

## Author
Jędrzej Sikora  
[GitHub](https://github.com/piegoose) · [LinkedIn](https://www.linkedin.com/in/jedrzej-sikora)
