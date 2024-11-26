package ru.delivery_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.delivery_project.services.SecurityService;
import ru.delivery_project.services.db.DBConnection;

import java.io.IOException;
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("title", "Регистрация");
        req.getRequestDispatcher("WEB-INF/html/registration.jsp").forward(req, res);

    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if (SecurityService.register(req, res, DBConnection.getInstance())) {
            req.getRequestDispatcher("WEB-INF/html/registration.jsp").forward(req, res);
            res.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Something went wrong");
        }


    }
}
