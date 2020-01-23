<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Admin Panel</title>
    <style>
        td{

            padding: 5px;
        }

    </style>
</head>

<body>

<%@include file="/WEB-INF/adminHeader.jspf"%>
<table>
    <thead>
        <th>Nazwa</th>
        <th>Opis</th>
        <th>Akcja</th>
    </thead>
    <c:forEach items="${institutions}" var="institution">
        <tr>
            <td>${institution.name}</td>
            <td>${institution.description}</td>
            <td><a href="/institution/edit/${institution.id}">Edytuj</a>/<a href="/institution/delete/${institution.id}">Usuń</a></td>
        </tr>
    </c:forEach>

</table>

<a href="/institution/add">Dodaj Instytucję</a>

</body>