<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:bundle basename="localization.msg" prefix="admin.new.showtime.">
    <fmt:message key="date" var="dateLoc"/>
    <fmt:message key="movie" var="movieLoc"/>
    <fmt:message key="timeSlot" var="timeSlotLoc"/>
    <fmt:message key="submit" var="submitLoc"/>
</fmt:bundle>

<html>
<head>
    <title>New ShowTime</title>
</head>
<body>

<c:import url="../jsp/header.jsp"/>

<form method="post" action="home?command=add_new_showtime">
    <table>
        <tr>
            <td align="left">${dateLoc}</td>
            <td align="left"><input placeholder="yyyy-mm-dd" type="text" name="Date" required="required" maxlength="10"/></td>
        </tr>
        <tr>
            <td align="left"> ${movieLoc} </td>
            <td align="left">
                <select name="movie">
                    <c:forEach items="${movies}" var="movie">
                        <option value="${movie.id}">${movie.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td align="left"> ${timeSlotLoc} </td>
            <td align="left">
                <select name="timeSlot">
                    <c:forEach items="${timeSlots}" var="timeSlot">
                        <option value="${timeSlot.id}">${timeSlot.from}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    </table>
    <div class="col-md-4">
        <button id="singlebutton" name="singlebutton" class="btn btn-primary">${submitLoc}</button>
    </div>
</form>

</body>
</html>
