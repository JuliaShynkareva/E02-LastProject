<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Добро пожаловать!</title>
        <link href="/resources/css/style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div id="containerLogin">
            <div id="loginForm">
                <h1>АВТОРИЗАЦИЯ</h1>
                <font color="#dc143c">
                    <c:if test="${not empty error}">
                        Не верно введен логин или пароль
                    </c:if>
                </font>
                <form name="loginForm" action="j_spring_security_check" method="post">
                    <input type="text" name="user_login" placeholder="Логин"/>
                    <input type="password" name="password_login" placeholder="Пароль"/><br/>
                    <input type="checkbox" name="remember-me-parameter" id="rememberCheck"/> Запомнить меня<br/>
                    <input type="submit"  value="ВОЙТИ"/>
                </form>
                <a href="/reg">Регистрация</a>
            </div>
        </div>
    </body>
</html>
