<%--@elvariable id="error_massage" type="antlr"--%>
<%--@elvariable id="succ_massage" type="antlr"--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/dashboard_style.css">
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
                       href="${pageContext.request.contextPath}/customer/add_order">Add New Order</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/customer/dashboard">Dashboard</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/">Logout</a>
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
                        <th colspan="7">
                            orders informations
                        </th>
                        <th colspan="3">
                            actions
                        </th>
                    </tr>
                    <tr>
                        <th>
                            sub service
                        </th>
                        <th>
                            description
                        </th>
                        <th>
                            final price
                        </th>
                        <th>
                            status
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
                            show suggestions
                        </th>
                        <th>
                            pay
                        </th>
                        <th>
                            add feedback
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
                                    ${order.description}
                            </td>
                            <td>
                                    ${order.finalPrice}
                            </td>
                            <td>
                                    ${order.orderStatus}
                            </td>
                            <td>
                                    ${order.registrationDate}
                            </td>
                            <td>
                                    ${order.toBeDoneDate}
                            </td>
                            <td>
                                    ${order.address.country}, ${order.address.city}, ${order.address.state}
                            </td>
                            <td>
                                <a href="/customer/show_order_suggestions_sortedByExpertAndPrice/${order.identity}">click
                                    me</a>
                            </td>
                            <td>
                                <c:if test="${order.orderStatus.toString() eq 'DONE'}">
                                    <a href="/customer/paying_online/${order.identity}">online</a> or <a
                                        href="/customer/paying_from_credit/${order.identity}">credit</a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${order.orderStatus.toString() eq 'PAID'}">
                                    <a href="/customer/show_feedback_page/${order.identity}">click me</a>
                                </c:if>
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