package ru.delivery_project.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityService {
    public static Boolean isSigned(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("signed")){
                return true;
            }
        }
        return false;
    }
    public static Boolean signIn(HttpServletRequest req, HttpServletResponse resp){
        resp.addCookie(new Cookie("signed", "true"));
        return true;
    }
    public static Boolean signOut(HttpServletRequest req, HttpServletResponse resp){
        if (isSigned(req)) {
            for(Cookie cookie : req.getCookies()) {
                if(cookie.getName().equals("signed")) {
                    cookie.setMaxAge(0); // Устанавливаем куку как истёкшую
                    cookie.setPath("/"); // Указываем путь, чтобы кука была удалена корректно
                    resp.addCookie(cookie);
                }
            }
        }
        return true;
    }
    public static Boolean register(HttpServletRequest req, HttpServletResponse resp){
        resp.addCookie(new Cookie("signed", "true"));
        return true;
    }
}
