package database;

import java.sql.*;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/security_scanner";
    private static final String USER = "root";
    private static final String PASSWORD = "Mayank041107@"; // your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}