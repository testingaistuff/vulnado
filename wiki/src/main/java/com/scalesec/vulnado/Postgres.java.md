# Postgres.java Documentation

## Overview
The `Postgres` class is a utility for managing PostgreSQL database connections, schema setup, and data manipulation. It provides methods to establish a connection to a PostgreSQL database, initialize the database schema, insert seed data, and perform hashing operations using MD5.

---

## Technology Overview

### Imports and Packages
| **Import/Package** | **Description** |
|---------------------|-----------------|
| `java.sql.Connection` | Represents a connection to a database. Used for executing SQL queries and managing transactions. |
| `java.sql.DriverManager` | Provides methods to establish a connection to a database using a JDBC driver. |
| `java.sql.PreparedStatement` | Represents a precompiled SQL statement. Used for executing parameterized queries securely. |
| `java.sql.Statement` | Represents a SQL statement. Used for executing static SQL queries. |
| `java.math.BigInteger` | Provides operations for large integer arithmetic. Used here to convert MD5 hash byte arrays into hexadecimal strings. |
| `java.security.MessageDigest` | Provides cryptographic hash functions. Used for generating MD5 hashes of input strings. |
| `java.security.NoSuchAlgorithmException` | Exception thrown when a requested cryptographic algorithm is not available. |
| `java.util.UUID` | Provides methods for generating universally unique identifiers (UUIDs). Used for creating unique primary keys. |

### Package
| **Package** | **Description** |
|-------------|-----------------|
| `com.scalesec.vulnado` | Custom package namespace for the application. Indicates the code belongs to the "vulnado" project under the "scalesec" organization. |

---

## Class Structure

### Data Structures
The class contains the following data structures:
1. **Database Schema**:
   - `users`: Stores user information such as `user_id`, `username`, `password`, `created_on`, and `last_login`.
   - `comments`: Stores comments with fields `id`, `username`, `body`, and `created_on`.

### Logic
The class contains the following logical methods:
1. **Database Connection**:
   - `connection()`: Establishes a connection to the PostgreSQL database using environment variables for configuration.
   
2. **Database Setup**:
   - `setup()`: Initializes the database schema, cleans up existing data, and inserts seed data.

3. **MD5 Hashing**:
   - `md5(String input)`: Generates an MD5 hash for a given input string.

4. **Data Insertion**:
   - `insertUser(String username, String password)`: Inserts a new user into the `users` table with a hashed password.
   - `insertComment(String username, String body)`: Inserts a new comment into the `comments` table.

---

## Method Details

### `connection()`
- **Purpose**: Establishes a connection to the PostgreSQL database.
- **Logic**:
  - Uses the `DriverManager` to connect to the database.
  - Retrieves connection parameters (`PGHOST`, `PGDATABASE`, `PGUSER`, `PGPASSWORD`) from environment variables.
  - Loads the PostgreSQL JDBC driver (`org.postgresql.Driver`).
- **Error Handling**: Prints stack trace and exits the program if an exception occurs.

### `setup()`
- **Purpose**: Sets up the database schema and inserts seed data.
- **Logic**:
  - Creates tables `users` and `comments` if they do not exist.
  - Deletes existing data from both tables.
  - Inserts predefined users and comments using `insertUser` and `insertComment` methods.
- **Error Handling**: Prints the exception and exits the program if an error occurs.

### `md5(String input)`
- **Purpose**: Generates an MD5 hash for a given input string.
- **Logic**:
  - Uses `MessageDigest` to compute the MD5 hash.
  - Converts the hash byte array into a hexadecimal string.
  - Pads the hash to ensure it is 32 characters long.
- **Error Handling**: Throws a `RuntimeException` if the MD5 algorithm is unavailable.

### `insertUser(String username, String password)`
- **Purpose**: Inserts a new user into the `users` table.
- **Logic**:
  - Generates a unique `user_id` using `UUID`.
  - Hashes the password using the `md5` method.
  - Executes a parameterized SQL query using `PreparedStatement`.
- **Error Handling**: Prints stack trace if an exception occurs.

### `insertComment(String username, String body)`
- **Purpose**: Inserts a new comment into the `comments` table.
- **Logic**:
  - Generates a unique `id` using `UUID`.
  - Executes a parameterized SQL query using `PreparedStatement`.
- **Error Handling**: Prints stack trace if an exception occurs.

---

## Insights

### Security Concerns
1. **MD5 Hashing**:
   - MD5 is considered cryptographically weak and vulnerable to collision attacks. It is not recommended for password hashing in modern applications. Consider using stronger algorithms like bcrypt, PBKDF2, or Argon2.

2. **Environment Variables**:
   - The database connection relies on environment variables for sensitive information (`PGUSER`, `PGPASSWORD`). Ensure these variables are securely managed and not exposed.

3. **SQL Injection**:
   - The use of `PreparedStatement` mitigates SQL injection risks. However, ensure all user inputs are validated before processing.

### Scalability
- The current implementation is suitable for small-scale applications. For larger systems, consider implementing connection pooling to optimize database performance.

### Error Handling
- The program exits (`System.exit(1)`) on critical errors, which may not be ideal for production systems. Consider implementing a more robust error-handling mechanism.

### Code Maintainability
- The `setup()` method combines schema creation, data cleanup, and seed data insertion. Splitting these operations into separate methods can improve readability and maintainability.

### Dependency Management
- The class depends on the PostgreSQL JDBC driver (`org.postgresql.Driver`). Ensure the driver is included in the project's dependencies and compatible with the database version.

---

## Environment Variables

| **Variable**   | **Description**                          |
|-----------------|------------------------------------------|
| `PGHOST`       | Hostname of the PostgreSQL server.       |
| `PGDATABASE`   | Name of the PostgreSQL database.         |
| `PGUSER`       | Username for database authentication.    |
| `PGPASSWORD`   | Password for database authentication.    |
