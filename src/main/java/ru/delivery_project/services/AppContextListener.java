package ru.delivery_project.services;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.delivery_project.services.db.DBConnection;

import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DBConnection dbConnection = DBConnection.getInstance();

        sce.getServletContext().setAttribute("dbConnection", dbConnection);

        System.out.println("Database connection established");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DBConnection dbConnection = (DBConnection) sce.getServletContext().getAttribute("dbConnection");

        try {
            if (dbConnection != null && dbConnection.getConnection() != null) {
                dbConnection.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("failed to close database connection", e);
        }
    }
}
