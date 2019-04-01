<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li><a href="<spring:url value="/"/>">Home</a></li>

<sec:authorize var="user"/>

<sec:authorize access="isAnonymous()">
    <li><a href="<spring:url value="/login"/>">Login</a></li>
    <li><a href="<spring:url value="/registration"/>">Registration</a></li>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
        <li><a href="<spring:url value="/cart/"/>">Cart</a></li>
        <li><a href="<spring:url value="/logout"/>">Logout</a></li>
</sec:authorize>