<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h4><a href="home?command=GetAllMovies">HOME</a></h4>

<form method="post" action="home?command=Login">
    <div class="form-group">
        <label class="col-md-2">Login:</label>
        <input placeholder="login" id="Login" name="Login" required="required"/>
    </div>
    <div class="form-group">
        <label class="col-md-2">Password:</label>
        <input type="password" placeholder="password" id="Password" name="Password" required="required"/>
    </div>
    <div class="col-md-4">
        <button id="singlebutton" name="singlebutton" class="btn btn-primary">SUBMIT</button>
    </div>
</form>

</body>
</html>
