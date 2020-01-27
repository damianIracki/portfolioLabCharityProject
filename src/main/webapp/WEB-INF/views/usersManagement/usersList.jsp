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
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
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
    <th>Email</th>
    <th>Imie</th>
    <th>Nazwisko</th>
    <th>Aktywny?</th>
    <th>Akcja</th>
    </thead>



    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.email}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.active}</td>
            <td><a href="/usersManagement/edit/${user.id}">Edytuj</a>/
                <c:choose>
                    <c:when test="${user.active==true}">
                        <a href="/usersManagement/ban/${user.id}">Ban</a>
                    </c:when>
                    <c:when test="${user.active==false}">
                        <a href="/usersManagement/ban/${user.id}">Unban</a>
                    </c:when>
                    <c:otherwise>
                        <a>A to nie wiem</a>
                    </c:otherwise>
                </c:choose>
                /<a href="/usersManagement/delete/${user.id}">Usuń</a></td>
        </tr>
    </c:forEach>

</table>


</body>
