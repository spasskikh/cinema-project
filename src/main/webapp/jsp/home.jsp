<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"
          prefix="c" %>

<html>

<head>
    <title>Movies</title>
</head>

<body>
<h2 align="center">Best Cinema</h2>


<h4><a href="home?command=view_login_page">LOGIN</a>   <a href="home?command=view_registration_page">REGISTER</a></h4>

<c:out value="${user.login}" />

<table>
    <c:forEach items="${movies}" var="movie">
        <tr>
            <td><a href="home?command=Movie">${movie.name}</a></td>
        </tr>
    </c:forEach>
</table>
</body>

</html>