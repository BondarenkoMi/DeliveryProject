<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="_header.jsp"%>

<div class="signin_form">
    <form method="post">
        <input type="email" required placeholder="Email">
        <input type="password" required placeholder="Пароль">
        <button type="submit">Войти</button>
    </form>
    <p>Еще не зарегистрированы?</p>
    <a href="<c:url value="/registration"/>">Зарегистрироваться</a>
</div>
</body>
<%@include file="_footer.jsp"%>
