<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Dashboard</title>
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
                    <a href="${pageContext.request.contextPath}/portal/admin/dashboard/add_service">Add new Service to
                        System</a>
                    <a href="${pageContext.request.contextPath}/portal/admin/dashboard/add_subservice">Add new Sub
                        Service to System</a>
                    <a href="${pageContext.request.contextPath}/portal/admin/dashboard/search">Users</a>
                    <a href="${pageContext.request.contextPath}/portal/admin/dashboard/show_orders">Orders History</a>
                    <a href="${pageContext.request.contextPath}/portal/admin/dashboard/show_services_history">Services
                        History</a>
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
                    <h1>Admin Dashboard</h1>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
