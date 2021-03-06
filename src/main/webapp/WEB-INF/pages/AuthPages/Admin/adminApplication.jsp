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
              <h1>Администратор</h1>
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

              <c:if test="${application.departmentId ne null}">

                  <h2>Отдел</h2>
                  <p>${application.departmentId.name}</p>

                  <c:if test="${empty application.employeeId}">
                      <form action="/admin/viewapp/editempl" method="get">
                          <input type="hidden" name="appId" value="${application.id}">
                          <select name="selEmpl">
                              <c:forEach items="${emplList}" var="employee">
                                  <option value="${employee.id}">${employee.surname}</option>
                              </c:forEach>
                          </select>
                          <input type="submit" value="Назначить исполнителя"/>
                      </form>
                  </c:if>

              </c:if>

              <c:if test="${empty application.departmentId}">

                  <form action="/admin/viewapp/editdep" method="get">
                      <input type="hidden" name="appId" value="${application.id}">
                      <select name="selDep">
                          <option disabled="true" value="0">Выберите Отдел</option>
                          <option value="4">Quality Assurance</option>
                          <option value="3">SAP Consulting</option>
                          <option value="1">System Development</option>
                          <option value="2">Web Development</option>
                      </select>
                      <input type="submit"  value="Назначить отдел"/>
                  </form>

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
          <a href="/admin">Вернуться к списку заявок</a>
      </div>
  </body>
</html>
