package ru.delivery_project.services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            String dbUrl = "jdbc:postgresql://localhost:5432/delivery";
            String user = "postgres";
            String password = "new_password";
            connection = DriverManager.getConnection(dbUrl, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed to connect to database");
            e.printStackTrace();  // Выводим стек трассировки для диагностики
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
