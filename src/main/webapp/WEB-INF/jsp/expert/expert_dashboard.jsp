<%--@elvariable id="expertDto" type="antlr"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Expert Dashboard</title>
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
                       href="${pageContext.request.contextPath}/sign_out">Logout</a>
                </li>

            </ul>
        </div>

    </div>
</nav>
<div class="main">
    <div class="row">
        <div class="col-2">
            <div class="custom-sidebar">
                <div>
                    <a href="${pageContext.request.contextPath}/expert/add_subservice">add new sub service</a>
                    <a href="${pageContext.request.contextPath}/expert/show_tasks">Tasks</a>
                </div>
            </div>
        </div>
        <div class="col-10">
            <div class="col-12">
                <div class="w-100 h-100 rounded pricing-text">
                    <h1></h1>
                </div>
            </div>
            <div class="col-12">
                <div class="w-100 h-100 rounded pricing-text">
                    <h1></h1>
                </div>
            </div>
            <div class="col-12">
                <div class="w-100 h-100 rounded pricing-text">
                    <h1>Hi ${expertDto.firstName}</h1>
                </div>
            </div>
            <div class="col-12">
                <div class="w-100 h-100 rounded Quickly-text">
                    <p>We are so glad to have you as our Expert.</p>
                </div>
            </div>

            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-8 p-5 w-100 h-100 rounded" style="margin-left: 17%">
                <table class="table table-bordered table-striped text-dark">
                    <thead>
                    <tr>
                        <th colspan="2" class="text-center" style="color: white; background-color: #fb771a">
                            your information
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            first name : ${expertDto.firstName}
                        </td>
                        <td>
                            last name : ${expertDto.lastName}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            email : ${expertDto.email}
                        </td>
                        <td>
                            status : ${expertDto.userStatus}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            registration date : ${expertDto.registrationDate}
                        </td>
                        <td>
                            credit : ${expertDto.credit}
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
