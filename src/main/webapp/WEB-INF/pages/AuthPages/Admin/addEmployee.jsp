<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Регистрация сотрудника</title>
        <link href="<c:url value="/resources/css/style.css"/>" type="text/css" rel="stylesheet">
    </head>
    <body>
        <div id="header">
            <div id="userData">
                <h1>Администратор</h1>
                <p><a href="/j_spring_security_logout">Выход</a></p>
            </div>
        </div>
        <div id="content">
            <div id="loginForm">
                <h1>РЕГИСТРАЦИЯ НОВОГО СОТРУДНИКА</h1>
                <form:form modelAttribute="employeeNew" action="/admin/addempl" method="post">
                    <form:input path="login" placeholder="Логин"/>
                    <font color="#dc143c"><form:errors path="login"/></font>
                    <form:password path="password" placeholder="Пароль"/>
                    <font color="#dc143c"><form:errors path="password"/></font>
                    <form:input path="name" placeholder="Имя сотрудника"/>
                    <font color="#dc143c"><form:errors path="name"/></font>
                    <form:input path="lastname" placeholder="Отчество сотрудника"/>
                    <font color="#dc143c"><form:errors path="lastname"/></font>
                    <form:input path="surname" placeholder="Фамилия сотрудника"/>
                    <font color="#dc143c"><form:errors path="surname"/></font>
                    <form:select path="department">
                        <form:option disabled="true" value="0" label="Выберите Отдел"/>
                        <form:option value="4" label="Quality Assurance"/>
                        <form:option value="3" label="SAP Consulting"/>
                        <form:option value="1" label="System Development"/>
                        <form:option value="2" label="Web Development"/>
                    </form:select>
                    <input type="submit"  value="ЗАРЕГИСТРИРОВАТЬ"/>
                </form:form>
            </div>
            <a href="/admin">Вернуться к списку заявок</a>
        </div>
    </body>
</html>
