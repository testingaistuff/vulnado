# Documentation for `User.java`

## Metadata
- **File Name**: `User.java`

---

## Package and Imports

### Package
- **`com.scalesec.vulnado`**: This package likely represents a module or namespace within the application. It is used to organize related classes and interfaces.

### Imports
| **Import**                          | **Description**                                                                 |
|-------------------------------------|---------------------------------------------------------------------------------|
| `java.sql.Connection`               | Provides an interface for managing a connection to a database.                 |
| `java.sql.Statement`                | Used to execute SQL queries against a database.                                |
| `java.sql.ResultSet`                | Represents the result of a database query, allowing iteration over rows.       |
| `io.jsonwebtoken.Jwts`              | A utility class from the `jsonwebtoken` library for creating and parsing JWTs. |
| `io.jsonwebtoken.JwtParser`         | Used to parse and validate JSON Web Tokens (JWTs).                             |
| `io.jsonwebtoken.SignatureAlgorithm`| Enum defining signature algorithms for signing JWTs.                           |
| `io.jsonwebtoken.security.Keys`     | Provides utility methods for generating cryptographic keys.                    |
| `javax.crypto.SecretKey`            | Represents a cryptographic key used for signing and verifying JWTs.            |

---

## Class: `User`

### Overview
The `User` class represents a user entity in the application. It provides methods for token generation, authentication, and fetching user data from a database. The class encapsulates user-related attributes and operations.

---

### Attributes
| **Attribute**       | **Type**   | **Description**                                                                 |
|---------------------|------------|---------------------------------------------------------------------------------|
| `id`                | `String`   | Represents the unique identifier of the user.                                  |
| `username`          | `String`   | Stores the username of the user.                                               |
| `hashedPassword`    | `String`   | Stores the hashed password of the user for authentication purposes.            |

---

### Constructor
#### `User(String id, String username, String hashedPassword)`
- **Purpose**: Initializes a new `User` object with the provided `id`, `username`, and `hashedPassword`.
- **Parameters**:
  - `id`: The unique identifier of the user.
  - `username`: The username of the user.
  - `hashedPassword`: The hashed password of the user.

---

### Methods

#### `String token(String secret)`
- **Purpose**: Generates a JSON Web Token (JWT) for the user using the provided secret key.
- **Parameters**:
  - `secret`: A string used as the secret key for signing the JWT.
- **Returns**: A signed JWT containing the user's username as the subject.
- **Implementation Details**:
  - Uses `Keys.hmacShaKeyFor` to generate a cryptographic key from the secret.
  - Uses `Jwts.builder` to create and sign the JWT.

---

#### `static void assertAuth(String secret, String token)`
- **Purpose**: Validates a given JWT using the provided secret key.
- **Parameters**:
  - `secret`: The secret key used to validate the JWT.
  - `token`: The JWT to be validated.
- **Throws**: `Unauthorized` exception if the token is invalid.
- **Implementation Details**:
  - Uses `Keys.hmacShaKeyFor` to generate a cryptographic key from the secret.
  - Uses `Jwts.parser` to parse and validate the JWT.
  - Catches exceptions during validation and throws a custom `Unauthorized` exception.

---

#### `static User fetch(String un)`
- **Purpose**: Fetches a user from the database based on the provided username.
- **Parameters**:
  - `un`: The username of the user to be fetched.
- **Returns**: A `User` object representing the fetched user, or `null` if no user is found.
- **Implementation Details**:
  - Establishes a database connection using `Postgres.connection()`.
  - Executes a SQL query to fetch user data based on the username.
  - Constructs a `User` object using the retrieved data.
  - Closes the database connection after the operation.

---

## Insights

### Security Concerns
1. **SQL Injection Vulnerability**:
   - The `fetch` method constructs SQL queries using string concatenation, which makes it vulnerable to SQL injection attacks. Using prepared statements is recommended to mitigate this risk.

2. **Hardcoded Secret Key**:
   - The `token` and `assertAuth` methods rely on a secret key provided as a string. Ensure the secret is securely managed and not hardcoded in the application.

3. **Exception Handling**:
   - The `assertAuth` method catches all exceptions and throws a custom `Unauthorized` exception. This approach may obscure the root cause of the error. Consider logging detailed error information.

### Cryptographic Practices
- The use of `Keys.hmacShaKeyFor` and `SignatureAlgorithm` ensures secure cryptographic operations for JWT signing and validation.

### Database Connection Management
- The `fetch` method closes the database connection after use, but it does not handle potential exceptions during the `close` operation. Using a `try-with-resources` block is recommended for better resource management.

### Scalability
- The `fetch` method retrieves only one user based on the username. If the application needs to handle large-scale user data, consider optimizing database queries and connection pooling.

### Code Design
- The `User` class combines data representation (attributes) and business logic (methods). Separating these concerns into different classes (e.g., DAO for database operations) can improve maintainability and testability.
