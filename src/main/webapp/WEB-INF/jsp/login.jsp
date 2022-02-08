<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/login_style.css"/>">
</head>
<body>
<div class="d-lg-flex half">
    <div class="bg order-1 order-md-2" style="background-image: url('/images/bg_1.jpg');"></div>
    <div class="contents order-2 order-md-1">
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-md-7">
                    <h3>Login to <strong>Home Service Provider System</strong></h3>
                    <p class="mb-4">Thanks for choosing us ^_^</p>

                    <form action="/login" method="post">
                        <div class="form-group first">
                            <label>
                                <input type="text" name="username" placeholder="username"/>
                            </label>
                            <br>
                        </div>
                        <div class="form-group last mb-3">
                            <label>
                                <input type="password" name="password" placeholder="password"/>
                            </label>
                            <br>
                        </div>

                        <div class="d-flex mb-5 align-items-center">
                            <label class="control control--checkbox mb-0"><span class="caption">Remember me</span>
                                <input type="checkbox" checked="checked"/>
                                <div class="control__indicator"></div>
                            </label>

                        </div>
                        <p class="mb-4">Don't have an account yet? <a
                                href="${pageContext.request.contextPath}/register">Register!</a></p>
                        <input type="submit" value="Login" class="btn btn-block btn-primary"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
</body>
</html>