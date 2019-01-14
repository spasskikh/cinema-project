<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:bundle basename="localization.msg" prefix="order.ticket.">
    <fmt:message key="order" var="orderLoc"/>
</fmt:bundle>

<html>
<head>
    <title>Seats</title>
</head>
<body>

<c:import url="../jsp/header.jsp"/>

${orderStatus}<br/>

<table>
    <tr>
        <td>${showTime.date}</td>
        <td>${showTime.timeSlot.from}</td>
        <td>${showTime.movie.name}</td>
    </tr>
</table>

<table>
    <tr>
        <c:forEach items="${seats}" var="seat">
            <td>
                <form method="post" action="home?command=order_ticket&showTimeId=${showTime.id}">
                    <button type='submit' name='seatId' value=${seat.id}>${seat.number}</button>
                </form>
            </td>
        </c:forEach>
    </tr>

</table>

</body>
</html>
