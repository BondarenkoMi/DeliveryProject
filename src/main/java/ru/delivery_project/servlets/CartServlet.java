package ru.delivery_project.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.delivery_project.db.DBConnection;
import ru.delivery_project.db.dao.Product;
import ru.delivery_project.db.dao.ProductInCart;
import ru.delivery_project.services.CartService;
import ru.delivery_project.services.NoProductException;
import ru.delivery_project.services.OrderService;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "cartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        List<ProductInCart> products = CartService.getCart(req);
        if (products.isEmpty()) {
            req.setAttribute("message", "Корзина пуста");
        }
        req.setAttribute("products", products);

        req.setAttribute("title", "Корзина");
        req.getRequestDispatcher("/WEB-INF/html/cart.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action.equals("checkout")) {
            try {
                OrderService.createOrder(req);
            } catch (NoProductException e) {
                req.setAttribute("message", e.getMessage());
                List<ProductInCart> products = CartService.getCart(req);
                req.setAttribute("products", products);
                req.setAttribute("title", "Корзина");
                req.getRequestDispatcher("/WEB-INF/html/cart.jsp").forward(req, res);
            }
            res.sendRedirect(req.getContextPath() + "/cart");
        }
        else {
            int cartId = Integer.parseInt(action.split(":")[1]);
            CartService.removeFromCart(cartId);
            res.sendRedirect(req.getContextPath() + "/cart");
        }



    }
}
