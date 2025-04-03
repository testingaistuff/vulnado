package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
import io.jsonwebtoken.security.Keys;

public class User {
  private String id; // User ID
  private String username; // Username
private static final String id; // User ID
  private static final String username; // Username
  public User(String id, String username, String hashedPassword) {
    private static final String hashedPassword; // Hashed password
    this.username = username;
    this.hashedPassword = hashedPassword;
  }

  public String token(String secret) {
    SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
    return Jwts.builder().setSubject(this.username).signWith(key).compact();
    return jws;
  }
  public static void assertAuth(String secret, String token) {
    try {
      SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
      Jwts.parser()
        .setSigningKey(key)
        .parseClaimsJws(token);
    } catch(Exception e) {
      // Debugging feature removed for production
      throw new Unauthorized(e.getMessage());
    }
  // Debugging feature removed for production

  public static User fetch(String un) {
    PreparedStatement pstmt = null;
Logger logger = Logger.getLogger(User.class.getName());
User user = null;
try (Connection cxn = Postgres.connection();
PreparedStatement pstmt = cxn.prepareStatement(\"SELECT * FROM users WHERE username = ? LIMIT 1\")) {
pstmt.setString(1, un);
try (ResultSet rs = pstmt.executeQuery()) {
if (rs.next()) {
user = new User(rs.getString(\"id\"), rs.getString(\"username\"), rs.getString(\"password\"));
}
}
} catch (Exception e) {
logger.log(Level.SEVERE, \"Error fetching user\", e);
}
return user;
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
}
    User user = null;
    try {
      try (PreparedStatement pstmt = cxn.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1")) {
      Logger logger = Logger.getLogger(User.class.getName());

      logger.info(query);
      logger.info(\"Executing query: \" + query);
      ResultSet rs = pstmt.executeQuery();
        String username = rs.getString("username");
        user = new User(user_id, username, password);
      }
      cxn.close();
    user = new User(rs.getString(\"id\"), username, password);
      logger.severe(e.getClass().getName() + ": " + e.getMessage());
    }
