<%@ page import="ru.delivery_project.services.SecurityService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${title}</title>
    <link rel="stylesheet" href="<c:url value="/resources/styles/styles.css"/>">
</head>
<body>
<div class="header">

    <div class="logo">
        <a class="logo" href="<c:url value="/"/>">

            <div>
                <img src="<c:url value="/resources/svg/truck.svg"/>">

                <p class="logoTextSmall">Fast Delivery</p>
            </div>

        </a>
    </div>
    <div class="info">

        <h1>Fast Delivery</h1>


        <h2>Быстрая доставка товаров из заграницы</h2>
    </div>

    <div class="links">

        <%
            Cookie[] cookies = request.getCookies();
            if (SecurityService.isSigned(request)) {
        %>
        <a href="<c:url value='/signout'/>">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-closed"
                 viewBox="0 0 16 16">
                <path d="M3 2a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3zm1 13h8V2H4z"/>
                <path d="M9 9a1 1 0 1 0 2 0 1 1 0 0 0-2 0"/>
            </svg>
            Выйти
        </a>
        <%
        } else {
        %>
        <a href="<c:url value='/signin'/>">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-door-closed"
                 viewBox="0 0 16 16">
                <path d="M3 2a1 1 0 0 1 1-1h8a1 1 0 0 1 1 1v13h1.5a.5.5 0 0 1 0 1h-13a.5.5 0 0 1 0-1H3zm1 13h8V2H4z"/>
                <path d="M9 9a1 1 0 1 0 2 0 1 1 0 0 0-2 0"/>
            </svg>
            Войти
        </a>
        <%
            }
        %>

        <a href="<c:url value="/cart"/>">
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bag"
                 viewBox="0 0 16 16">
                <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1m3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1z"/>
            </svg>
            Корзина</a>

    </div>
</div>
