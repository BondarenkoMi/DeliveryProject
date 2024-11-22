package ru.delivery_project.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.delivery_project.services.SecurityService;

import java.io.IOException;
@WebServlet(name = "cartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {


        if(!SecurityService.isSigned(req)) {
            res.setStatus(401);
            req.setAttribute("title", "401 Unauthorized");
            req.setAttribute("message", "Войдите в систему и попробуйте еще раз");
            req.getRequestDispatcher("WEB-INF/html/401.jsp").forward(req, res);
        }else {
            req.setAttribute("title","Корзина");
            req.getRequestDispatcher("/WEB-INF/html/cart.jsp").forward(req, res);
        }
    }
}
