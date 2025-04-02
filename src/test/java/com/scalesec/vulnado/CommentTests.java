import org.junit.jupiter.api.*;
import org.mockito.*;
import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentTest {

    @BeforeEach
    void setUp() {
        // Mock the Postgres connection
        MockedStatic<Postgres> mockedPostgres = mockStatic(Postgres.class);
        Connection mockConnection = mock(Connection.class);
        mockedPostgres.when(Postgres::connection).thenReturn(mockConnection);
    }

    @Test
    void create_ShouldReturnComment_WhenCommitSucceeds() throws SQLException {
        // Arrange
        String username = "testUser";
        String body = "testBody";
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        Connection mockConnection = Postgres.connection();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Act
        Comment comment = Comment.create(username, body);

        // Assert
        assertNotNull(comment, "Comment should not be null");
        assertEquals(username, comment.username, "Username should match");
        assertEquals(body, comment.body, "Body should match");
    }

    @Test
    void create_ShouldThrowBadRequest_WhenCommitFails() throws SQLException {
        // Arrange
        String username = "testUser";
        String body = "testBody";
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        Connection mockConnection = Postgres.connection();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Act & Assert
        assertThrows(BadRequest.class, () -> Comment.create(username, body), "Should throw BadRequest when commit fails");
    }

    @Test
    void fetchAll_ShouldReturnListOfComments_WhenQuerySucceeds() throws SQLException {
        // Arrange
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("id")).thenReturn("testId");
        when(mockResultSet.getString("username")).thenReturn("testUser");
        when(mockResultSet.getString("body")).thenReturn("testBody");
        when(mockResultSet.getTimestamp("created_on")).thenReturn(new Timestamp(new Date().getTime()));

        Connection mockConnection = Postgres.connection();
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Act
        List<Comment> comments = Comment.fetch_all();

        // Assert
        assertNotNull(comments, "Comments list should not be null");
        assertEquals(1, comments.size(), "Comments list should contain one comment");
        assertEquals("testUser", comments.get(0).username, "Username should match");
    }

    @Test
    void delete_ShouldReturnTrue_WhenDeletionSucceeds() throws SQLException {
        // Arrange
        String id = "testId";
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        Connection mockConnection = Postgres.connection();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Act
        boolean result = Comment.delete(id);

        // Assert
        assertTrue(result, "Delete should return true when deletion succeeds");
    }

    @Test
    void delete_ShouldReturnFalse_WhenDeletionFails() throws SQLException {
        // Arrange
        String id = "testId";
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        Connection mockConnection = Postgres.connection();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        // Act
        boolean result = Comment.delete(id);

        // Assert
        assertFalse(result, "Delete should return false when deletion fails");
    }
}
