# Documentation: `LoginController.java`

## Overview
The `LoginController.java` file implements a RESTful API endpoint for user authentication. It uses the Spring Boot framework to handle HTTP requests and responses. The controller validates user credentials and generates a token for successful authentication. Unauthorized access is handled with custom exceptions.

---

## Technology Overview

### Imports and Packages
| **Import/Package** | **Description** |
|---------------------|-----------------|
| `com.scalesec.vulnado` | Custom package for the application. It encapsulates the `LoginController` and related classes. |
| `org.springframework.boot.*` | Provides core Spring Boot functionality, including application startup and configuration. |
| `org.springframework.http.HttpStatus` | Represents HTTP status codes, used for defining response statuses. |
| `org.springframework.web.bind.annotation.*` | Contains annotations for mapping HTTP requests to handler methods in controllers. |
| `org.springframework.boot.autoconfigure.*` | Enables auto-configuration of Spring Boot applications based on dependencies. |
| `org.springframework.stereotype.*` | Provides annotations for defining Spring-managed components, such as `@RestController`. |
| `org.springframework.beans.factory.annotation.*` | Includes annotations like `@Value` for injecting configuration properties. |
| `java.io.Serializable` | Marker interface for enabling serialization of objects, used in `LoginRequest` and `LoginResponse`. |

---

## Code Structure

### Data Structures
1. **`LoginRequest`**
   - Represents the incoming request payload for the `/login` endpoint.
   - Implements `Serializable` for object serialization.
   - Fields:
     - `username`: The username provided by the client.
     - `password`: The password provided by the client.

2. **`LoginResponse`**
   - Represents the response payload for the `/login` endpoint.
   - Implements `Serializable` for object serialization.
   - Fields:
     - `token`: The authentication token generated for the user.
   - Constructor:
     - Accepts a `String` message to initialize the `token`.

3. **`Unauthorized`**
   - Custom exception class for handling unauthorized access.
   - Annotated with `@ResponseStatus(HttpStatus.UNAUTHORIZED)` to return a 401 status code.
   - Constructor:
     - Accepts an exception message.

---

### Logic Implementation
1. **`LoginController`**
   - **Annotations**:
     - `@RestController`: Marks the class as a REST controller.
     - `@EnableAutoConfiguration`: Enables Spring Boot's auto-configuration feature.
     - `@Value("${app.secret}")`: Injects the `app.secret` property from the application's configuration.
     - `@CrossOrigin(origins = "*")`: Allows cross-origin requests from any domain.
   - **Endpoint**:
     - **Path**: `/login`
     - **Method**: `POST`
     - **Consumes**: `application/json`
     - **Produces**: `application/json`
   - **Logic**:
     - Fetches the user details using `User.fetch(input.username)`.
     - Validates the password by comparing the hashed input password (`Postgres.md5(input.password)`) with the stored hashed password (`user.hashedPassword`).
     - If validation succeeds, generates a token using `user.token(secret)` and returns it in a `LoginResponse`.
     - If validation fails, throws an `Unauthorized` exception with the message "Access Denied".

---

## Insights

### Security Considerations
- **Password Hashing**: The code uses `Postgres.md5` for hashing passwords. MD5 is considered cryptographically weak and should be replaced with a stronger hashing algorithm like bcrypt or Argon2.
- **Token Generation**: The token generation logic (`user.token(secret)`) is abstracted. Ensure that the token is securely generated and follows best practices (e.g., JWT with proper signing and expiration).
- **Cross-Origin Requests**: The `@CrossOrigin(origins = "*")` annotation allows requests from any domain, which may expose the API to security risks. Consider restricting origins to trusted domains.

### Scalability
- **Database Interaction**: The `User.fetch` method and `Postgres.md5` suggest direct interaction with a database. Ensure that these methods are optimized for performance and scalability.
- **Serialization**: Both `LoginRequest` and `LoginResponse` implement `Serializable`, which is useful for distributed systems but may introduce overhead. Evaluate if serialization is necessary for the current use case.

### Error Handling
- **Custom Exception**: The `Unauthorized` class provides a clean way to handle unauthorized access. Consider extending this approach for other error scenarios (e.g., `BadRequest`, `InternalServerError`).

### Configuration Management
- **Secret Injection**: The `@Value("${app.secret}")` annotation injects the application's secret key. Ensure that this key is securely stored and managed (e.g., using environment variables or a secrets management tool).

### Maintainability
- **Modular Design**: The separation of request, response, and exception classes improves code readability and maintainability.
- **Annotations**: The use of Spring annotations simplifies configuration and reduces boilerplate code.

---

## File Metadata
| **File Name** | **Description** |
|---------------|-----------------|
| `LoginController.java` | Implements a RESTful API endpoint for user authentication. |
