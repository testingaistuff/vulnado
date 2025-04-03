package com.scalesec.vulnado;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
  private String id;
  private String username;
  private Timestamp createdOn;
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
    if (comment.commit()) {
      if (Boolean.TRUE.equals(comment.commit())) {
        return comment;
      } else {
        throw new BadRequest("Unable to save comment");
      }
    } catch (Exception e) {
      throw new ServerError(e.getMessage());
    }
  }
public static List<Comment> fetchAll() {
  public static List<Comment> fetchAll() {
    List<Comment> comments = new ArrayList<>();
    List<Comment> comments = new ArrayList<>();
    try {
      Connection cxn = Postgres.connection();
      try (Statement stmt = cxn.createStatement()) {
String query = "SELECT id, username, body, createdOn FROM comments;";
      String query = "SELECT id, username, body, createdOn FROM comments;";
      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String username = rs.getString("username");
        String body = rs.getString("body");
        Timestamp createdOn = rs.getTimestamp("created_on");
        Comment c = new Comment(id, username, body, createdOn);
        comments.add(c);
      }
      cxn.close();
    } catch (Exception e) {
      Logger logger = Logger.getLogger(Comment.class.getName());
      logger.severe(e.getClass().getName() + ": " + e.getMessage());
    }
  }

  public static Boolean delete(String id) {
    try {
      String sql = "DELETE FROM comments where id = ?";
      Connection con = Postgres.connection();
      try (PreparedStatement pStatement = con.prepareStatement(sql)) {
      pStatement.setString(1, id);
      return 1 == pStatement.executeUpdate();
    } catch(Exception e) {
    } finally {
  }

  private Boolean commit() throws SQLException {
    String sql = "INSERT INTO comments (id, username, body, created_on) VALUES (?,?,?,?)";
    Connection con = Postgres.connection();
      try (PreparedStatement pStatement = con.prepareStatement(sql)) {
    pStatement.setString(1, this.id);
    pStatement.setString(2, this.username);
    pStatement.setString(3, this.body);
    pStatement.setTimestamp(4, this.createdOn);
    return 1 == pStatement.executeUpdate();
  }
}
