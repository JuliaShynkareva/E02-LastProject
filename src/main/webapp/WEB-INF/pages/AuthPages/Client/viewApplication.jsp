<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Просмотр заявки</title>
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
          <div id="applicationView">
              <h1>${application.id}. ${application.name}</h1>
              <h2>Описание</h2><p>${application.description}</p>
              <h2>Дата подачи</h2><p>${application.applicationDate}</p>
              <c:if test="${application.employeeId ne null}">
                  <h2>Исполнитель</h2>
                  <p>${application.employeeId.name} ${application.employeeId.lastname} ${application.employeeId.surname}</p>
              </c:if>
              <c:if test="${application.changeDate ne null}">
                  <div id="applicationUpdate">
                      <h2>Дата изменения</h2><p>${application.changeDate}</p>
                      <h2>Статус</h2><p>${application.statusId.name}</p>
                      <c:if test="${application.comment ne null}">
                          <h2>Комментарий исполнителя</h2><p>${application.comment}</p>
                      </c:if>
                  </div>
              </c:if>
          </div>
          <a href="/client">Вернуться к списку заявок</a>
      </div>
  </body>
</html>
