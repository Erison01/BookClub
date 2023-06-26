
<%--
  Created by IntelliJ IDEA.
  User: Dell Latitude E7450
  Date: 6/26/2023
  Time: 12:26 PM
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
    <h1>Add a Book to Your shelf</h1>
    <a href="/books">back to shelves</a>
</div>
<div>
    <form:form action="/books" modelAttribute="book" class="form" method="post">

        <div class="form-row">
            <form:label path="title">Title</form:label>
            <form:input path="title"></form:input>
            <form:errors path="title" class="text-danger"></form:errors>
        </div>

        <div class="form-row">
            <form:label path="author">Author</form:label>
            <form:input path="author"></form:input>
            <form:errors path="author" class="text-danger"></form:errors>
        </div>

        <div class="form-row">
            <form:label path="thoughts">My Thoughts</form:label>
            <form:textarea path="thoughts"></form:textarea>
            <form:errors path="thoughts" class="text-danger"></form:errors>
        </div>

        <div class="form-row">
            <form:input type="hidden"  path="user" value="${user.id}"/>
            <form:errors path="user" class="error"/>
        </div>

        <div class="form-row">

            <input type="submit" value="Submit" class="btn-primary">

        </div>

    </form:form>
</div>
</body>
</html>
