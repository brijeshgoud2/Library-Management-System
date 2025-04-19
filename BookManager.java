package major.Book;

import java.sql.*;

public class BookManager {

	private final String url = "jdbc:mysql://localhost:3306/librarydb";
    private final String user = "root";
    private final String password = "nikndhuku23"; // replace with your password

    // Connect to DB
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Add book
    public void addBook(String title, String author, String isbn, String status) {
        String sql = "INSERT INTO books (title, author, isbn, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setString(4, status);
            stmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Error: ISBN already exists.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Display books
    public void displayBooks() {
        String sql = "SELECT * FROM books";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Book List ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", Title: " + rs.getString("title")
                        + ", Author: " + rs.getString("author")
                        + ", ISBN: " + rs.getString("isbn")
                        + ", Status: " + rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Update status
    public void updateBookStatus(int id, String status) {
        String sql = "UPDATE books SET status=? WHERE id=?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book status updated.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Delete book
    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id=?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("Book not found.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
