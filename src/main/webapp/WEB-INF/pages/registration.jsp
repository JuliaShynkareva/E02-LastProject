<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Регистрация</title>
        <link href="<c:url value="/resources/css/style.css"/>" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div id="container">
            <div id="loginForm">
                <h1>РЕГИСТРАЦИЯ</h1>
                <form:form modelAttribute="clientNew" action="/reg" method="post">
                    <form:input path="login" placeholder="Логин"/>
                    <font color="#dc143c"><form:errors path="login"/></font>
                    <form:password path="password" placeholder="Пароль"/>
                    <font color="#dc143c"><form:errors path="password"/></font>
                    <form:input path="name" placeholder="Ваше имя"/>
                    <font color="#dc143c"><form:errors path="name"/></font>
                    <form:input path="lastname" placeholder="Ваше отчество"/>
                    <font color="#dc143c"><form:errors path="lastname"/></font>
                    <form:input path="surname" placeholder="Ваша фамилия"/>
                    <font color="#dc143c"><form:errors path="surname"/></font>
                    <input type="submit"  value="ЗАРЕГИСТРИРОВАТЬСЯ"/>
                </form:form>
            </div>
            <a href="/">Уже есть аккаунт</a>
        </div>
    </body>
</html>
