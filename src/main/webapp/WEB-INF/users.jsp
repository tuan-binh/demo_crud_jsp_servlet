<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: binh
  Date: 9/30/2024
  Time: 8:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%-- JSTL Jakata Standed Tag Library --%>
<%-- parameter (key = value) --%>
<a href="<%=request.getContextPath()%>/user-servlet?action=add" class="btn btn-primary">ADD USERS</a>
<table class="table table-striped table-inverse table-responsive">
    <thead class="thead-inverse">
    <tr>
        <th>STT</th>
        <th>NAME</th>
        <th>DOB</th>
        <th>ADDRESS</th>
        <th>PHONE</th>
        <th colspan="2">ACTIONS</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${usersList}" var="u" varStatus="loop">
        <tr>
            <td scope="row">${loop.count}</td>
            <td>${u.name}</td>
            <td>${u.dateOfBirth}</td>
            <td>${u.address}</td>
            <td>${u.phone}</td>
            <td>
                <a href="<%=request.getContextPath()%>/user-servlet?action=edit&id=${u.id}">Edit</a>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/user-servlet?action=delete&id=${u.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
