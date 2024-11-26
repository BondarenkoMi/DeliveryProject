package ru.delivery_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.delivery_project.services.SecurityService;
import ru.delivery_project.services.db.DBConnection;

import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("title", "hello");
        req.getRequestDispatcher("/WEB-INF/html/index.jsp").forward(req, res);
    }
}
