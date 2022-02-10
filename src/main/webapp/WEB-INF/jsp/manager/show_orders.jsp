<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>search and filter users</title>
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
                       href="${pageContext.request.contextPath}/portal/admin/dashboard">Dashboard</a>
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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="col-12">
        <form id="search-form">
            <table class="table table-bordered table-striped text-dark text-center">
                <thead>
                <tr>
                    <th colspan="5" style="text-align: center; border: none">
                        Filter Orders
                    </th>
                </tr>
                <tr>
                    <th>
                        <label>from date</label>
                    </th>
                    <th>
                        <label>to date</label>
                    </th>
                    <th>
                        <label>status</label>
                    </th>
                    <th>
                        <label>service name</label>
                    </th>
                    <th>
                        <label>sub service name</label>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>

                    <td>
                        <label>
                            <input type="text" name="fromDate" id="fromDate"/>
                        </label>
                    </td>

                    <td>
                        <label>
                            <input type="text" name="toDate" id="toDate"/>
                        </label>
                    </td>

                    <td>
                        <label>
                            <input type="text" name="status" id="status"/>
                        </label>
                    </td>

                    <td>
                        <label>
                            <input type="text" name="serviceName" id="serviceName"/>
                        </label>
                    </td>

                    <td>
                        <label>
                            <input type="text" name="subServiceName" id="subServiceName"/>
                        </label>
                    </td>
                </tr>
                </tbody>
            </table>
            <input id="submit_input" type="submit" value="Filter ${name}'s Order" class="btn btn-block btn-primary"/>
        </form>
    </div>
</nav>
<br><br>

<div class="contents order-2 order-md-1" id="response" style="display: none">
    <div class="container">
        <div class="row align-items-center justify-content-center mb-5">
            <div class="col-12">
                <table class="table table-bordered table-striped text-dark text-center" style="margin-left: -6%">
                    <thead>
                    <tr>
                        <th colspan="7">
                            result
                        </th>
                    </tr>
                    <tr>
                        <th>
                            Order status
                        </th>
                        <th>
                            registration date
                        </th>
                        <th>
                            to be done date
                        </th>
                        <th>
                            address
                        </th>
                        <th>
                            expert username
                        </th>
                        <th>
                            description
                        </th>
                        <th>
                            final price
                        </th>
                    </tr>
                    </thead>
                    <tbody id="response-body">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/manager.js"/>"></script>
</body>
</html>