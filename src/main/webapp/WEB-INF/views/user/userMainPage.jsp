<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>UserMainPage</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<%@include file="/WEB-INF/userHeader.jspf"%>


<sec:authorize access="isAuthenticated()">
    <h1>Zalogowano jako użytkownik: <sec:authentication property="name"/></h1><br>
</sec:authorize>

</body>