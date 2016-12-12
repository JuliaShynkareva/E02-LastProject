<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Создание заявки</title>
      <link href="<c:url value="/resources/css/style.css"/>" type="text/css" rel="stylesheet">
  </head>
  <body>
      <div id="header">
          <div id="userData">
              <h1>${client.name} ${client.lastname} ${client.surname}</h1>
              <p><a href="/j_spring_security_logout">Выход</a></p>
          </div>
      </div>
      <div id="content">
          <div id="applicationForm">
              <h1>Создание заявки</h1>
              <form:form modelAttribute="appNew" action="/client/addapp" method="post">
                  <form:input path="name" placeholder="Наименование заявки"/>
                  <font color="#dc143c"><form:errors path="name"/></font>
                  <form:input path="description" placeholder="Описание"/>
                  <font color="#dc143c"><form:errors path="description"/></font>
                  <input type="submit"  value="СОЗДАТЬ"/>
              </form:form>
          </div>
          <a href="/client">Вернуться к списку заявок</a>
      </div>
  </body>
</html>
