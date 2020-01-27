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
    <title>Zmień hasło</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<%@include file="/WEB-INF/userHeader.jspf"%>

<h1>Zmień hasło</h1>

<form:form modelAttribute="changePasswordDto" method="put">
    Stare hasło: <form:password path="oldPassword"/><br>
    Nowe hasło: <form:password path="newPassword"/><br>
    Powtórz nowe hasło: <form:password path="confirmPassword"/><br>
    <input type="submit" value="Zapisz">
</form:form>

</body>