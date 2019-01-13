<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:bundle basename="localization.msg" prefix="admin.new.movie.">
    <fmt:message key="name" var="nameLoc"/>
    <fmt:message key="description" var="descriptionLoc"/>
    <fmt:message key="year" var="yearLoc"/>
    <fmt:message key="duration" var="durationLoc"/>
    <fmt:message key="submit" var="submitLoc"/>
</fmt:bundle>
<html>
<head>
    <title>New Movie</title>
</head>
<body>

<c:import url="../jsp/header.jsp"/>

<form method="post" action="home?command=add_new_movie">
    <table>
        <tr>
            <td align="left">${nameLoc}</td>
            <td align="left"><input type="text" name="Name" required="required" maxlength="100"/></td>
        </tr>
        <tr>
            <td align="left"> ${descriptionLoc} </td>
            <td align="left"><input type="text" name="Description" required="required" maxlength="1000"/></td>
        </tr>
        <tr>
            <td align="left"> ${yearLoc} </td>
            <td align="left"><input type="number" name="Year" required="required" min="1950"/></td>
        </tr>
        <tr>
            <td align="left"> ${durationLoc} </td>
            <td align="left"><input type="number" name="Duration" required="required" min="0" max="240"/></td>
        </tr>
    </table>
    <div class="col-md-4">
        <button id="singlebutton" name="singlebutton" class="btn btn-primary">${submitLoc}</button>
    </div>
</form>

</body>
</html>
