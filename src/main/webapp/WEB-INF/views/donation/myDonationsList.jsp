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
    <title>My Donations</title>
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
    <style>
        td{
            padding: 5px;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/userHeader.jspf"%>

<h1>Moje dotacje:</h1>
<table>
    <thead>
    <th>Create date</th>
    <th>Pick up date</th>
    <th>Pick up time</th>
    <th>Categories</th>
    <th>Received</th>
    <th>Bags quantity</th>
    <th>Comment</th>
    </thead>
    <c:forEach items="${myDonations}" var="donation">
        <tr>
            <td>${donation.createDate}</td>
            <td>${donation.pickUpDate}</td>
            <td>${donation.pickUpTime}</td>
            <td>
                <ul>
                    <c:forEach items="${donation.categories}" var="category">
                        <li>${category.name}</li>
                    </c:forEach>
                </ul>
            </td>
            <td>${donation.isReceived()}</td>
            <td>${donation.quantity}</td>
            <td>${donation.pickUpComment}</td>
            <td><a href="/user/donationDetails/${donation.id}">Szczegóły</a> </td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<a href="/donation">Przekaż dotację</a>
</body>