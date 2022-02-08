<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/dashboard_style.css"/>">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light box-shadow-style">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="#">Home Service Provider System</a>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        </ul>
        <div class="d-flex align-items-center">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/customer/dashboard">Dashboard</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/sign_out">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br><br>
<div class="contents order-2 order-md-1">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-7">
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <form action="${pageContext.request.contextPath}/customer/update_password" method="post">
                    <table class="table table-bordered table-striped text-dark text-center">
                        <thead>
                        <tr>
                            <th colspan="2">
                                Changing Password Form
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <label>username</label>
                            </td>
                            <td>
                                <label>
                                    <input type="text" name="userName"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>password</label>
                            </td>
                            <td>
                                <label>
                                    <input type="password" name="oldPass"
                                           pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
                                           title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>new password</label>
                            </td>
                            <td>
                                <label>
                                    <input type="password" name="newPass"
                                           pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{8}$"
                                           title="the password must be at least 8 character, with a lower case, an upper case and no whitespace"/>
                                </label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Submit" class="btn btn-block btn-primary"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>