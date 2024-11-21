<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="_header.jsp"%>

<div class="registration_form">
  <form method="post">
    <input type="text" required placeholder="Имя">
    <input type="text" required placeholder="Фамилия">
    <input type="email" required placeholder="Email">
    <input type="tel" required placeholder="Номер телефона">
    <input type="password" required placeholder="Пароль">
    <button type="submit">Зарегистрироваться</button>
    <button type="reset">Сбросить</button>
  </form>
</div>
</body>
<%@include file="_footer.jsp"%>
