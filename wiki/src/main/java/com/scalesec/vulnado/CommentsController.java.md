# Documentation: CommentsController.java

## Overview
The `CommentsController` class is a RESTful controller designed to handle HTTP requests related to comments. It provides endpoints for retrieving, creating, and deleting comments. The controller leverages Spring Boot and its annotations to simplify configuration and routing. Additionally, it includes custom exception handling for specific HTTP status codes.

---

## Technology Overview

### Imports and Packages
| **Import/Package** | **Purpose** |
|---------------------|-------------|
| `org.springframework.http.HttpStatus` | Provides HTTP status codes for response handling. Used for custom exception annotations. |
| `org.springframework.web.bind.annotation.*` | Contains annotations for mapping HTTP requests to handler methods in the controller. |
| `org.springframework.beans.factory.annotation.*` | Provides dependency injection annotations like `@Value` for accessing application properties. |
| `org.springframework.boot.autoconfigure.*` | Enables auto-configuration of Spring Boot applications. |
| `java.util.List` | Represents a collection of comments returned by the `GET` endpoint. |
| `java.io.Serializable` | Ensures that the `CommentRequest` class can be serialized, which is necessary for transferring data over HTTP. |

---

## Class Structure

### CommentsController
The `CommentsController` class is annotated with `@RestController` and `@EnableAutoConfiguration`, making it a Spring Boot REST controller with automatic configuration. It defines three endpoints:

#### Endpoints
| **HTTP Method** | **Path**         | **Description**                                                                 |
|------------------|------------------|---------------------------------------------------------------------------------|
| `GET`           | `/comments`      | Retrieves all comments. Requires authentication via the `x-auth-token` header. |
| `POST`          | `/comments`      | Creates a new comment. Requires authentication and a JSON payload.             |
| `DELETE`        | `/comments/{id}` | Deletes a comment by its ID. Requires authentication via the `x-auth-token`.   |

#### Key Features
- **Authentication**: All endpoints require an `x-auth-token` header for authentication. The `User.assertAuth` method validates the token using a secret value injected from application properties.
- **Cross-Origin Resource Sharing (CORS)**: The `@CrossOrigin` annotation allows requests from any origin (`origins = "*"`) to interact with the API.
- **Dynamic Configuration**: The `@Value("${app.secret}")` annotation injects the `app.secret` property from the application's configuration file.

---

### Supporting Classes

#### CommentRequest
A simple data structure implementing `Serializable`. It represents the payload for creating a new comment. The class contains:
- `username`: The name of the user creating the comment.
- `body`: The content of the comment.

#### Custom Exceptions
| **Class**       | **HTTP Status**         | **Purpose**                                                                 |
|------------------|-------------------------|-----------------------------------------------------------------------------|
| `BadRequest`    | `HttpStatus.BAD_REQUEST` | Represents client-side errors (e.g., invalid input).                       |
| `ServerError`   | `HttpStatus.INTERNAL_SERVER_ERROR` | Represents server-side errors (e.g., unexpected failures).                 |

---

## Insights

1. **Security Considerations**:
   - The `x-auth-token` header is used for authentication, but the implementation of `User.assertAuth` is not provided. Ensure that token validation is robust and secure.
   - The `@CrossOrigin(origins = "*")` annotation allows requests from any origin, which may expose the API to security risks. Consider restricting origins to trusted domains.

2. **Scalability**:
   - The `Comment.fetch_all()` method retrieves all comments, but its scalability depends on the underlying implementation. For large datasets, consider implementing pagination.

3. **Error Handling**:
   - Custom exceptions (`BadRequest` and `ServerError`) improve error clarity but lack detailed logging or error codes. Enhance these exceptions with additional metadata for debugging.

4. **Data Validation**:
   - The `CommentRequest` class does not enforce validation on its fields (`username` and `body`). Consider adding validation annotations (e.g., `@NotNull`, `@Size`) to ensure data integrity.

5. **Dependency Injection**:
   - The use of `@Value` for injecting the `app.secret` property is straightforward but may benefit from a more structured configuration class for managing application secrets.

6. **Extensibility**:
   - The controller is tightly coupled with the `Comment` class. To improve extensibility, consider introducing a service layer to handle business logic independently of the controller.

---

## File Metadata
| **File Name** | **Description** |
|---------------|-----------------|
| `CommentsController.java` | RESTful controller for managing comments in a Spring Boot application. |
