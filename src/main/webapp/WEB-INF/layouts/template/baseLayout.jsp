<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title><tiles:insertAttribute name="title"/></title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <link href="resources/css/equal-height-columns.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.1/angular.min.js"></script>
    <script src="/cinema/resources/js/controllers.js"></script>
</head>

<body>

<div class="container">
        <div class="pull-right" style="padding-right:10px">
            <a href="?language=en">ENG</a> | <a href="?language=ru">RU</a>
        </div>

    <div class="jumbotron">
        <div class="header">
            <ul class="nav nav-pills pull-right">
                <tiles:insertAttribute name="navigation"/>
            </ul>
            <h1 class="text-muted"><tiles:insertAttribute name="heading"/></h1>
        </div>
        <h4><tiles:insertAttribute name="tagline"/></h4>
    </div>

    <div class="row">
        <tiles:insertAttribute name="content"/>
    </div>
</div>

</body>
</html>
