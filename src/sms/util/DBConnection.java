package src.sms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Private constructor to prevent instantiation
    private DBConnection() 
    {
        
    }

    // Get a database connection
    public static Connection getConnection() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get credentials from .env
            String url = EnvLoader.get("DB_URL");
            String user = EnvLoader.get("DB_USER");
            String password = EnvLoader.get("DB_PASSWORD");

            if (url == null || user == null || password == null) {
                throw new RuntimeException("Database credentials not found in .env file.");
            }

            // Return the connection
            return DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database.", e);
        }
    }
}
