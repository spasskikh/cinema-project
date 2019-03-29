<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="resources/css/equal-height-columns.css" rel="stylesheet">

<section class="container">
    <div class="row row-eq-height">
        <c:forEach items="${movies}" var="movie">
            <div class="col-sm-6 col-md-4 col-xs-12" style="padding-bottom: 15px">
                <div class="thumbnail">
                    <img src="<c:url value="/img/${movie.id}.jpg"></c:url>" alt="image" style="width: 100%"/>
                    <div class="caption">
                        <h4>${movie.name}</h4>
                        <p>Year: ${movie.year}</p>
                        <p>Duration: ${movie.duration}</p>
                        <p>
                            <a href=" <spring:url value="/movies/${movie.id}" /> " class="btn btn-primary">
                                <span class="glyphicon-info-sign glyphicon"/></span> Details
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
