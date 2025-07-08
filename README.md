# Wise Demo

A mini Wise-style payment microservice showcasing Java, Spring Boot, Spring Data JPA, and H2 database.

## Features

* **Spring Boot** application setup with Maven
* **Constructor-based Dependency Injection**
* **JPA Entity** (`Payment`) and **Spring Data JPA** repository
* **RESTful API** with endpoints:

  * `POST /api/payments` - Create a new payment
  * `GET /api/payments` - Retrieve all payments
  * `GET /api/payments/user/{userId}` - Retrieve payments by user ID
* **Validation** with `@Valid` and **Global Exception Handling**
* **In-memory H2** database (configurable to file-based)

## Prerequisites

* Java 17 or higher
* Maven 3.6+

## Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/wise-demo.git
   cd wise-demo
   ```

2. **Configure Database**

   The default setup uses an in-memory H2 database. To persist data between restarts, in `src/main/resources/application.yml` change:

   ```yaml
   spring:
     datasource:
       url: jdbc:h2:file:./data/wise-demo;DB_CLOSE_DELAY=-1
   ```

3. **Build & Run**

   ```bash
   mvn clean package
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

4. **API Usage**

   * Create a payment:

     ```bash
     curl -X POST http://localhost:8080/api/payments \
       -H "Content-Type: application/json" \
       -d '{
         "userId":"<uuid>",
         "amount":100.50,
         "from":"USD",
         "to":"EUR"
       }'
     ```

   * Get all payments:

     ```bash
     curl http://localhost:8080/api/payments
     ```

   * Get payments by user:

     ```bash
     curl http://localhost:8080/api/payments/user/<uuid>
     ```

5. **H2 Console (Optional)**

   Enable H2 console in `application.yml`:

   ```yaml
   spring:
     h2:
       console:
         enabled: true
         path: /h2-console
   ```

   Access the console at `http://localhost:8080/h2-console`, JDBC URL `jdbc:h2:mem:devdb`.

## Future Improvements

* Add unit and integration tests using JUnit and Mockito
* Dockerize the application for containerized deployments
* Integrate Swagger UI or OpenAPI documentation for easy API exploration

