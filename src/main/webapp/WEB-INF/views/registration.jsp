<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please fill in registration form</h3>
                </div>
                <div class="panel-body">
                    <form action="<c:url value="/registration"/>" method="post" class="form-horizontal">
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="login"><i class="fa fa-user"></i></label>
                            <input type="text" class="form-control" id="login" name="login" placeholder="Enter Username"
                                   required>
                        </div>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter Password" required>
                        </div>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="matchingPassword"><i class="fa fa-lock"></i></label>
                            <input type="password" class="form-control" id="matchingPassword" name="matchingPassword"
                                   placeholder="Repeat Password" required>
                        </div>
                        <div class="form-actions">
                            <input type="submit" class="btn btn-block btn-primary btn-default" value="Register">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>