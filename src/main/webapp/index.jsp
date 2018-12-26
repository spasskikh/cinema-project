<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <jsp:forward page="/home">
    <jsp:param name="command" value="GetAllMovies"/>
  </jsp:forward>
  </body>
</html>
