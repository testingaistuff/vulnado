package com.scalesec.vulnado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;
import java.sql.Statement;
import java.sql.ResultSet;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public class User {
  private String id; // User ID
  private String username; // Username

  private String hashedPassword; // Hashed password
  public User(String id, String username, String hashedPassword) {
    this.id = id;
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
  }

  public static User fetch(String un) {
    PreparedStatement pstmt = null;
    User user = null;
    try {
      Connection cxn = Postgres.connection();
      try (PreparedStatement pstmt = cxn.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1")) {
      Logger logger = Logger.getLogger(User.class.getName());
      logger.info("Opened database successfully");

      String query = "select * from users where username = '" + un + "' limit 1";
      logger.info(query);
      PreparedStatement pstmt = cxn.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1");
      pstmt.setString(1, un);
      if (rs.next()) {
      ResultSet rs = pstmt.executeQuery();
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(user_id, username, password);
      }
      cxn.close();
    } catch (Exception e) {
      e.printStackTrace();
      logger.severe(e.getClass().getName() + ": " + e.getMessage());
    } finally {
    }
  }
}
