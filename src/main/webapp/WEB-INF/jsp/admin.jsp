<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="localization.msg">
    <fmt:message key="viewAttendanceReport" var="viewAttendanceReportLoc"/>
    <fmt:message key="addNewMovie" var="addNewMovieLoc"/>
    <fmt:message key="addNewShowTime" var="addNewShowTimeLoc"/>
    <fmt:message key="cancelShowTime" var="cancelShowTimeLoc"/>
</fmt:bundle>
<html>
<head>
    <title>Admin</title>
</head>


<body>

<c:import url="../jsp/header.jsp" />

<h3><a href="home?command=attendance_report&page=1">${viewAttendanceReportLoc}</a></h3>
<h3><a href="home?command=add_new_movie">${addNewMovieLoc}</a></h3>
<h3><a href="home?command=add_new_showtime">${addNewShowTimeLoc}</a></h3>
<h3><a href="home?command=cancel_showtime">${cancelShowTimeLoc}</a></h3>
</body>
</html>
