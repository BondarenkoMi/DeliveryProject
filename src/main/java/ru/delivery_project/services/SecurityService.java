package ru.delivery_project.services;

import io.jsonwebtoken.Claims;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.delivery_project.services.db.DBConnection;
import ru.delivery_project.services.jwt.JwtGenerator;
import ru.delivery_project.services.jwt.JwtValidator;

import java.io.IOException;
import java.sql.*;

public class SecurityService {
    public static boolean isSigned(HttpServletRequest req, HttpServletResponse res, DBConnection dbConnection) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return false;
        }

        for (Cookie cookie : cookies) {
            if ("jwt".equals(cookie.getName())) {
                String jwtCookie = cookie.getValue();

                try {
                    String email = JwtValidator.validate(jwtCookie).getSubject();
                    Connection connection = dbConnection.getConnection();
                    Statement statement = connection.createStatement();
                    String query = "select user_email, current_token from users where user_email = '" + email + "'";
                    ResultSet resultSet = statement.executeQuery(query);
                    if (resultSet.next()) {
                        String token = resultSet.getString("current_token");
                        if (token.equals(jwtCookie)) {
                            return true;
                        }
                    }
                } catch (SQLException | RuntimeException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return false;
    }
    public static boolean signIn(HttpServletRequest req, HttpServletResponse resp, DBConnection dbConnection){
        Connection conn = dbConnection.getConnection();
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String query = "select password_hash from users where user_email = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String passwordHash = rs.getString("password_hash");
                    if (PasswordSecurity.checkPassword(password, passwordHash)) {
                        String token = JwtGenerator.generateToken(email);
                        String updateQuery = "update users set current_token = ? where user_email = ?";
                        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
                            updateStatement.setString(1, token);
                            updateStatement.setString(2, email);
                            updateStatement.executeUpdate();
                        }
                        Cookie cookie = new Cookie("jwt", token);
                        cookie.setMaxAge(60 * 60 * 24);
                        cookie.setPath("/");
                        resp.addCookie(cookie);
                        return true;
                    } else {
                        req.setAttribute("message", "Wrong password");
                        req.getRequestDispatcher("WEB-INF/html/signin.jsp").forward(req, resp);
                        return false; // неверный пароль
                    }
                } else {
                    req.setAttribute("message", "User not found");
                    req.getRequestDispatcher("WEB-INF/html/signin.jsp").forward(req, resp);
                    return false; // пользователь не найден
                }
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при авторизации", e);
        }
    }

    public static void signOut(HttpServletRequest req, HttpServletResponse resp, DBConnection dbConnection){
        if (isSigned(req, resp, dbConnection)) {
            for(Cookie cookie : req.getCookies()) {
                if(cookie.getName().equals("jwt")) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
        }
    }
    public static boolean register(HttpServletRequest req, HttpServletResponse resp, DBConnection dbConnection){
        Connection conn = dbConnection.getConnection();
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String passwordHash = PasswordSecurity.hashPassword(password);
            String firstName = req.getParameter("first_name");
            String secondName = req.getParameter("second_name");
            String tel = req.getParameter("telephone");

            Statement statement = conn.createStatement();
            String query = "insert into users (user_first_name, user_second_name, user_email, telephone, password_hash) values ('"
                    + firstName + "', '"
                    + secondName + "', '"
                    + email + "', '"
                    + tel + "', '"
                    + passwordHash + "')";
            statement.executeUpdate(query);
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("failed to register user", e);
        }
    }
}
