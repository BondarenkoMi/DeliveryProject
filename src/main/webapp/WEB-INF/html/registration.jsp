<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="_header.jsp" %>

<div class="registration_form">
    <form method="post">
        <ul>
            <li>
                <input type="text" required placeholder="Имя">
            </li>
            <li>
                <input type="text" required placeholder="Фамилия">
            </li>
            <li>
                <input type="email" required placeholder="Email">
            </li>
            <li>
                <input type="tel" required placeholder="Номер телефона">
            </li>
            <li>
                <input type="password" required placeholder="Пароль">
            </li>
        </ul>
        <ul>
            <li>
                <button type="submit">Зарегистрироваться</button>
            </li>
            <li>
                <button type="reset">Сбросить</button>
            </li>

        </ul>


    </form>
</div>
</body>
<%@include file="_footer.jsp" %>
