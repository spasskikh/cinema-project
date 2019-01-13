<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:bundle basename="localization.msg" prefix="movie.info.">
    <fmt:message key="description" var="descriptionLoc"/>
    <fmt:message key="duration" var="durationLoc"/>
    <fmt:message key="year" var="yearLoc"/>
    <fmt:message key="order" var="orderLoc"/>
</fmt:bundle>
<html>
<head>
    <title>Movie</title>
</head>
<body>

<c:import url="../jsp/header.jsp"/>

<h3>${movie.name}</h3>
<table>
    <tr>
        <td>${yearLoc}</td>
        <td>${movie.year}</td>
    </tr>
    <tr>
        <td>${durationLoc}</td>
        <td>${movie.duration}</td>
    </tr>
    <tr>
        <td valign="top">${descriptionLoc}</td>
        <td><span style="max-width: 50%; display:block;" >${movie.description}</span></td>
    </tr>
</table>

<table>
    <c:forEach items="${showTimes}" var="showTime">
        <tr>
            <td>${showTime.date}</td>
            <td>${showTime.timeSlot.from}</td>
            <td><a href="home?command=order_ticket&showTimeId=${showTime.id}">${orderLoc}</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
