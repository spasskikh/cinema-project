<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="container" ng-app="moviesApp">
    <div ng-controller="moviesCtrl" ng-init="getAllMovies()">
        <div class="row row-eq-height">
            <div class="col-sm-6 col-md-4 col-xs-12" style="padding-bottom: 15px" ng-repeat="movie in movies">
                <div class="thumbnail">
                    <img src="<spring:url value="/img/{{movie.id}}.jpg"></spring:url>" alt="image" style="width: 100%"/>
                    <div class="caption">
                        <h4>{{movie.name}}</h4>
                        <p>Year: {{movie.year}}</p>
                        <p>Duration: {{movie.duration}}</p>
                        <p>
                            <a href=" <spring:url value="/movies/{{movie.id}}" /> " class="btn btn-primary">
                                <span class="glyphicon-info-sign glyphicon"/></span> Details
                            </a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

