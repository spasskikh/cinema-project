<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Welcome</title>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
</head>

<body>
<section class="container">
    <div class="pull-right" style="padding-right:50px">
        <a href="?language=en">ENG</a> | <a href="?language=ru">RU</a>
        <a href="<c:url value="/logout"/>">Logout</a>
    </div>
</section>

<div class="container">
    <div class="jumbotron">
        <div class="header">
            <h3>${greeting}</h3>
        </div>
    </div>
</div>
</body>
</html>
