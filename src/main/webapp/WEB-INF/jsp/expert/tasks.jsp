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
                       href="${pageContext.request.contextPath}/expert/show_orders">Add New Suggestion</a>
                </li>
                <li>
                    <a class="btn btn-outline-primary my-2 my-sm-0"
                       href="${pageContext.request.contextPath}/expert/dashboard">Dashboard</a>
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
                        <th colspan="8">
                            suggestions informations
                        </th>
                        <th colspan="3">
                            actions
                        </th>
                    </tr>
                    <tr>
                        <th colspan="4">
                            order
                        </th>
                        <th rowspan="2">
                            suggested price
                        </th>
                        <th rowspan="2">
                            start time
                        </th>
                        <th rowspan="2">
                            registration date
                        </th>
                        <th rowspan="2">
                            status
                        </th>
                        <th rowspan="2">
                            start
                        </th>
                        <th rowspan="2">
                            finish
                        </th>
                        <th rowspan="2">
                            customer feedback
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
                            registration date
                        </th>
                        <th>
                            address
                        </th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="suggestion" items="${expert_suggestions}">
                        <tr>
                            <td>
                                    ${suggestion.order.subService.name}
                            </td>
                            <td>
                                    ${suggestion.order.description}
                            </td>
                            <td>
                                    ${suggestion.order.registrationDate}
                            </td>
                            <td>
                                    ${suggestion.order.address.country}, ${suggestion.order.address.city}, ${suggestion.order.address.state}
                            </td>
                            <td>
                                    ${suggestion.suggestedPrice}
                            </td>
                            <td>
                                    ${suggestion.startTime}
                            </td>
                            <td>
                                    ${suggestion.registrationDate}
                            </td>
                            <td>
                                <c:if test="${suggestion.suggestionStatus.toString() eq 'ACCEPTED'}">
                                    <p style="color: green">${suggestion.suggestionStatus}</p>
                                </c:if>
                                <c:if test="${suggestion.suggestionStatus.toString() eq 'REJECTED'}">
                                    <p style="color: red">${suggestion.suggestionStatus}</p>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${suggestion.suggestionStatus.toString() eq 'ACCEPTED' and suggestion.order.orderStatus.toString() eq 'SPECIALIST_COMING_YOUR_PLACE'}">
                                    <a href="/expert/start/${suggestion.identity}">click me</a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${suggestion.suggestionStatus.toString() eq 'ACCEPTED' and suggestion.order.orderStatus.toString() eq 'STARTED'}">
                                    <a href="/expert/finish/${suggestion.identity}">click me</a>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${suggestion.suggestionStatus.toString() eq 'ACCEPTED' and suggestion.order.orderStatus.toString() eq 'FEEDEDBACK'}">
                                    <a href="/expert/show_feedback/${suggestion.identity}">click me</a>
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