      // Debugging feature removed for production
    }
  }

  public static User fetch(String un) {
    PreparedStatement pstmt = null;
    User user = null;
    try (Connection cxn = Postgres.connection()) {
      Logger logger = Logger.getLogger(User.class.getName());
      try (PreparedStatement pstmt = cxn.prepareStatement("SELECT * FROM users WHERE username = ? LIMIT 1")) {
      String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
      logger.info("Executing query: " + query);
      pstmt.setString(1, un);
      try (ResultSet rs = pstmt.executeQuery()) {
        String username = rs.getString("username");
        String password = rs.getString("password");
        user = new User(rs.getString("id"), rs.getString("username"), rs.getString("password"));
      logger.severe(e.getClass().getName() + ": " + e.getMessage());
  }
}
