<%--
  Created by IntelliJ IDEA.
  User: Dell Latitude E7450
  Date: 6/24/2023
  Time: 12:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<html>
<head>

  <title>Title</title>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="d-flex justify-content-between align-items-center m-4">
  <h1>Welcome ${user.userName}!</h1>
  <a href="/logout">Logout</a>
</div>
<div class="d-flex justify-content-between align-items-center m-4">
  <p>Books from everyone's shelves</p>
  <a href="/books/new">+ add to my shelf!</a>
</div>
<div class="m-4">
  <table class="table table-primary">
    <tr>
      <th>Id</th>
      <th>Title</th>
      <th>Author Name</th>
      <th>Posted by</th>
    </tr>
    <c:forEach var="book" items="${books}">
      <tr>
        <td><c:out value="${book.id}"/></td>
        <td><a href="/books/${book.id}">  <c:out value="${book.title}"/> </a></td>
        <td><c:out value="${book.author}"/></td>
        <td><c:out value="${book.user.userName}"/></td>
      </tr>
    </c:forEach>
  </table>
</div>
</body>
</html>
