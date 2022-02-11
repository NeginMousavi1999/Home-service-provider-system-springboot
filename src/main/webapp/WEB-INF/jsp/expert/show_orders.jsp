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
                    <a class="nav-link" href="${pageContext.request.contextPath}/expert/show_tasks">Back</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/expert/dashboard">Dashboard</a>
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
            <div class="col-12">
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <table class="table table-bordered table-striped text-dark text-center">
                    <thead>
                    <tr>
                        <th colspan="5">
                            orders information
                        </th>
                        <th colspan="2">
                            actions
                        </th>
                    </tr>
                    <tr>
                        <th>
                            sub service
                        </th>
                        <th>
                            base cost
                        </th>
                        <th>
                            description
                        </th>
                        <th>
                            registration date
                        </th>
                        <th>
                            address
                        </th>
                        <th>
                            show suggestions
                        </th>
                        <th>
                            add suggestion
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>
                                    ${order.subService.name}
                            </td>
                            <td>
                                    ${order.subService.cost}
                            </td>
                            <td>
                                    ${order.description}
                            </td>
                            <td>
                                    ${order.registrationDate}
                            </td>
                            <td>
                                    ${order.address.formattedAddress}
                            </td>
                            <td>
                                <a href="/expert/show_order_suggestions/${order.identity}">click me</a>
                            </td>
                            <td>
                                <a href="/expert/add_suggestion/${order.identity}">click me</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>