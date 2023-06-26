<%--
  Created by IntelliJ IDEA.
  User: Dell Latitude E7450
  Date: 6/26/2023
  Time: 2:20 PM
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
<h1 class="m-4"><c:out value="${book.title}"/></h1>

<div class="m-4">
  <p>Here are ${book.user.userName}'s thoughts</p>
  <p>${book.thoughts}</p>
</div>
<div class="m-4">
  <c:if test="${book.user.equals(user)}">
    <a href="/books/${book.id}/edit">Edit</a>
    <form:form action="/books/${book.id}/delete" method="delete">
    <button class="btn btn-danger">Delete</button>
    </form:form>

  </c:if>
</div>
</body>
</html>
