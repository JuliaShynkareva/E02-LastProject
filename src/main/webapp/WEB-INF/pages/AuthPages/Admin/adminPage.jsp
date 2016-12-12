<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Администратор</title>
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
            <p><a href="/admin/addempl">Регистрация нового сотрудника</a></p>
            <c:if test="${applicationList.size() > 0}">
                <table id="headerTable">
                    <tr>
                        <th title="Статус">Статус</th>
                        <th title="Наименование заявки">Наименование заявки</th>
                    </tr>
                    <c:forEach items="${applicationList}" var="app">
                        <tr>
                            <td>${app.statusId.name}</td>
                            <td><form action="/admin/viewapp" method="get">
                                <input type="hidden" name="appId" value="${app.id}">
                                <input type="submit" value="${app.name}">
                            </form></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${empty applicationList}">
                <div id="EmtyAppList"><p>Список заявок пуст</p></div>
            </c:if>
        </div>
    </body>
</html>
