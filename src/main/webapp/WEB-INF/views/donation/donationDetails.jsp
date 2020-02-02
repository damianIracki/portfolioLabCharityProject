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

</head>
<body>
<%@include file="/WEB-INF/userHeader.jspf"%>
Data utworzenia: ${donation.createDate}<br>
Data przekazania: ${donation.pickUpDate}<br>
Godzina przekazania: ${donation.pickUpTime}<br>
Ilość worków: ${donation.quantity}<br>
<p>Kategorie:
    <ul>
        <c:forEach items="${donation.categories}" var="category">
            <li>${category.name}</li>
        </c:forEach>
    </ul>
</p>
<p>Instytucja: ${donation.institution.name}</p>
<p>Status dotacji: ${donation.donationStatus.name}</p>
<c:if test="${donation.donationStatus.name == 'odebrane'}">
    <p>Data odebrania dotacji: ${donation.receivedDate}</p>
</c:if>

<p>Zmień status dotacji</p>
<form:form modelAttribute="donationStatus" method="post">
    <form:select path="id">
        <c:forEach items="${donationStatuses}" var="status">
            <form:option label="${status.name}" value = "${status.id}"/>
        </c:forEach>
    </form:select>
    <input type="submit" value="Zmień status"/>
</form:form>
<br>

<br>
<a href="/user/myDonations">Wróć do listy dotacji</a>
</body>