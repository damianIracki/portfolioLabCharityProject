<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Edytuj użytkownika</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<%@include file="/WEB-INF/adminHeader.jspf"%>

<h1>Edycja użytkownika ${user.email}</h1>

<form:form modelAttribute="user" method="put">
    Email: <form:input path="email"/><br>
    Imię: <form:input path="firstName"/><br>
    Nazwisko: <form:input path="lastName"/><br>
    <form:hidden path="id"/>
    <input type="submit" value="Zapisz">
</form:form>

</body>