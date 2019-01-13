<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:bundle basename="localization.msg" prefix="admin.attendance.">
    <fmt:message key="orderDate" var="orderDateLoc"/>
    <fmt:message key="user" var="userLoc"/>
    <fmt:message key="movie" var="movieLoc"/>
    <fmt:message key="date" var="dateLoc"/>
    <fmt:message key="time" var="timeLoc"/>
    <fmt:message key="seat" var="seatLoc"/>
</fmt:bundle>


<html>
<head>
    <title>Attendance Report</title>
</head>

<body>
<c:import url="../jsp/header.jsp"/>

<table cellspacing="2" border="1" cellpadding="5">
    <tr>
        <th>${orderDateLoc}</th>
        <th>${userLoc}</th>
        <th>${movieLoc}</th>
        <th>${dateLoc}</th>
        <th>${timeLoc}</th>
        <th>${seatLoc}</th>
    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.date}</td>
            <td>${order.user.login}</td>
            <td>${order.showTime.movie.name}</td>
            <td>${order.showTime.date}</td>
            <td>${order.showTime.timeSlot.from}</td>
            <td>${order.seat.number}</td>
        </tr>
    </c:forEach>
</table>

<table cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="home?command=attendance_report&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

</body>
</html>
