package ru.delivery_project.servlets;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.delivery_project.services.SecurityService;
import ru.delivery_project.services.db.DBConnection;

import java.io.IOException;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if (!SecurityService.isSigned(req, res, DBConnection.getInstance())) {
            req.setAttribute("title", "Войти");
            req.getRequestDispatcher("WEB-INF/html/signin.jsp").forward(req, res);
        } else {
            req.setAttribute("title","400 Bad Request");
            res.setStatus(400);
            req.setAttribute("message", "Вы уже авторизованы");
            req.getRequestDispatcher("WEB-INF/html/400.jsp").forward(req, res);
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if(SecurityService.signIn(req, res, DBConnection.getInstance())) {
            res.sendRedirect(req.getContextPath() + "/");
        }




    }
}
