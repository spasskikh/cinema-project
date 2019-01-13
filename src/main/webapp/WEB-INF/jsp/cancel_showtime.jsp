<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:bundle basename="localization.msg" prefix="admin.cancel.showtime.">
    <fmt:message key="date" var="datelLoc"/>
    <fmt:message key="movie" var="movielLoc"/>
    <fmt:message key="timeSlot" var="timeSlotLoc"/>
    <fmt:message key="cancel" var="cancelLoc"/>
</fmt:bundle>

<html>
<head>
    <title>Cancel ShowTime</title>
</head>
<body>

<c:import url="../jsp/header.jsp"/>

<table>
    <tr>
        <th>${datelLoc}</th>
        <th>${timeSlotLoc}</th>
        <th>${movielLoc}</th>
    </tr>
    <c:forEach items="${showTimes}" var="showTime">
        <tr>
            <td>${showTime.date}</td>
            <td>${showTime.timeSlot.from}</td>
            <td>${showTime.movie.name}</td>
            <td>
                <form method="post" action="home?command=cancel_showtime">
                    <button type='submit' name='showTimeId' value=${showTime.id}>${cancelLoc}</button>
                </form>
            </td>
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
                    <td><a href="home?command=cancel_showtime&page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>


</body>
</html>
