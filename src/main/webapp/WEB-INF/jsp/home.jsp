<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>

<html>

<head>
    <title>Movies</title>
</head>

<body>

<c:import url="../jsp/header.jsp" />

<table>
    <c:forEach items="${movies}" var="movie">
        <tr>
            <td><a href="home?command=view_movie_info&movieId=${movie.id}">${movie.name}</a></td>
        </tr>
    </c:forEach>
</table>
</body>

</html>