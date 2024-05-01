package triviagamee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

    // private static final String DATABASE_URL = "jdbc:sqlserver://your_server_address"; // Replace with your database server address
    // private static final String DATABASE_USERNAME = "your_username"; // Replace with your database username
    // private static final String DATABASE_PASSWORD = "your_password"; // Replace with your database password

    // public String checkCredentials(String username) throws SQLException {
    //     Connection connection = null;
    //     PreparedStatement statement = null;
    //     ResultSet resultSet = null;

    //     try {
    //         connection = getConnection();
    //         String sql = "SELECT password FROM users WHERE username = ?";
    //         statement = connection.prepareStatement(sql);
    //         statement.setString(1, username); // Set username parameter

    //         resultSet = statement.executeQuery();

    //         if (resultSet.next()) {
    //             return resultSet.getString("password"); // Return stored password if user exists
    //         } else {
    //             return null; // User not found
    //         }
    //     } finally {
    //         closeResources(connection, statement, resultSet);
    //     }
    // }

    // private Connection getConnection() throws SQLException {
    //     return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    // }

    // private void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) throws SQLException {
    //     if (resultSet != null) {
    //         resultSet.close();
    //     }
    //     if (statement != null) {
    //         statement.close();
    //     }
    //     if (connection != null) {
    //         connection.close();
    //     }
    // }
}
