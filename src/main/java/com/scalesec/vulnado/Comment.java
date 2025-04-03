package com.scalesec.vulnado;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
  private String id;
  private String username; // Corrected variable name
  private Timestamp createdOn; // Corrected variable name
  private String body;

  public Comment(String id, String username, String body, Timestamp createdOn) {
    this.id = id;
    this.username = username;
    this.body = body;
    this.createdOn = createdOn;
  }

  public static Comment create(String username, String body){
    long time = new Date().getTime();
    Timestamp timestamp = new Timestamp(time);
    Comment comment = new Comment(UUID.randomUUID().toString(), username, body, timestamp);
      if (comment.commit()) { // Corrected boolean expression
      if (Boolean.TRUE.equals(comment.commit())) {
        return comment;
      } else {
        throw new BadRequest("Unable to save comment");
      }
    } catch (Exception e) {
      throw new ServerError(e.getMessage());
    }
  }
  public static List<Comment> fetchAllComments() { // Corrected method name
  public static List<Comment> fetchAll() {
    Statement stmt = null; // Added generic type
    List<Comment> comments = new ArrayList<>();
    try {
      try (Connection cxn = Postgres.connection(); Statement stmt = cxn.createStatement()) { // Corrected resource management
      try (Statement stmt = cxn.createStatement()) {
      String query = "SELECT id, username, body, created_on FROM comments";
      String query = "SELECT id, username, body, created_on FROM comments"; // Removed SELECT *
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body"); // Renamed variable
        Timestamp createdOn = rs.getTimestamp("created_on");
        Comment c = new Comment(id, username, body, created_on);
        comments.add(c);
      }
      cxn.close();
      Logger logger = Logger.getLogger(Comment.class.getName()); // Added logger
      logger.severe(e.getClass().getName() + ": " + e.getMessage());
      logger.severe(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  public static Boolean deleteComment(String id) { // Corrected method name
    try {
      String sql = "DELETE FROM comments where id = ?";
      Connection con = Postgres.connection();
      try (Connection con = Postgres.connection(); PreparedStatement pStatement = con.prepareStatement(sql)) { // Corrected resource management
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
    } finally {
  }

  private Boolean commitComment() throws SQLException { // Corrected method name
    String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";
    Connection con = Postgres.connection();
      try (PreparedStatement pStatement = con.prepareStatement(sql)) {
    pStatement.setString(1, this.id); // Corrected variable name
    pStatement.setString(2, this.username);
    pStatement.setString(3, this.body);
    pStatement.setTimestamp(4, this.createdOn); // Corrected variable name
    return 1 == pStatement.executeUpdate();
  }
}
