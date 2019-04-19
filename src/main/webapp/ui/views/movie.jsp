<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container" ng-app="moviesApp">
    <div ng-controller="moviesCtrl" ng-init="getMovie('${movieId}')">
        <div class="col-md-5">
            <img src="<spring:url value="/img/${movieId}.jpg"></spring:url>" alt="image" style="width:100%"/>
        </div>
        <div class="col-md-5">
            <h3>{{movie.name}}</h3>
            <p style="text-align: justify">{{movie.description}}</p>
            <p><strong>Year : </strong>{{movie.year}}</p>
            <p><strong>Duration: </strong> : {{movie.duration}} </p>
            <p>
                <a href="<spring:url value="/"/>" class="btn btn-default">
                    <span class="glyphicon-hand-left glyphicon"></span> Back
                </a>
                <a href="#" class="btn btn-warning btn-large" ng-click="viewAvailableTicketsTickets('${movieId})">
                    <span class="glyphicon-shopping-cart glyphicon"></span> View Tickets
                </a>
                <%--<a href="<spring:url value="/cart" />" class="btn btn-default">--%>
                <%--<span class="glyphicon-hand-right glyphicon"></span> View Cart--%>
                <%--</a>--%>
            </p>
        </div>
    </div>
</div>