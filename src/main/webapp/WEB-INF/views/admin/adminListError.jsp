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

<h1 style="color: red">Musi pozostać conajmniej jeden admin!!!</h1><br><br>
<a href="/admin/panel">Wróć</a>
</body>