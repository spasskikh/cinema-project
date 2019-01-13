<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:import url="../jsp/header.jsp"/>

<form method="post" action="home?command=view_login_page">

    <table>
        <tr>
            <td align="left">
                Login:
            </td>
            <td align="right">
                <input placeholder="login" id="Login" name="Login" required="required"
                minlength="5" maxlength="20"/>
            </td>
        </tr>
        <tr>
            <td align="left">
                Password:
            </td>
            <td align="right">
                <input type="password" placeholder="password" id="Password" name="Password" required="required"
                minlength="5" maxlength="20"/>
            </td>
        </tr>
    </table>
    <div class="col-md-4">
        <button id="singlebutton" name="singlebutton" class="btn btn-primary">SUBMIT</button>
    </div>
</form>

</body>
</html>
