<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li><a href="<spring:url value="/"/>">Home</a></li>
<c:choose>
    <c:when test="${empty user}">
        <li><a href="<spring:url value="/login"/>">Login</a></li>
        <li><a href="<spring:url value="/registration"/>">Registration</a></li>
    </c:when>
    <c:otherwise>
        <li><a href="<spring:url value="/cart/"/>">Cart</a></li>
        <li><a href="<spring:url value="/logout"/>">Logout</a>)</li>
    </c:otherwise>
</c:choose>