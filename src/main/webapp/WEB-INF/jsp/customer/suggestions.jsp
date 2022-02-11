<%--@elvariable id="error_massage" type="antlr"--%>
<%--@elvariable id="succ_massage" type="antlr"--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/dashboard_style.css"/>">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
          integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
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
                    <a class="nav-link" href="${pageContext.request.contextPath}/customer/all_orders">Back</a>
                </li>
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
            <div class=" col-12">
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <table class="table table-bordered table-striped text-dark text-center">
                    <thead>
                    <tr>
                        <th colspan="7">
                            suggestion information
                        </th>
                        <th>
                            action
                        </th>
                    </tr>
                    <tr>
                        <th>
                            status
                        </th>
                        <th>
                            <a href="/customer/show_order_suggestions/expertScore/${identity}"><i
                                    class='fas fa-angle-down fa-lg'></i></a> expert score
                        </th>
                        <th>
                            expert name
                        </th>
                        <th>
                            <a href="/customer/show_order_suggestions/price/${identity}"><i
                                    class='fas fa-angle-down fa-lg'></i></a> suggested price
                        </th>
                        <th>
                            duration of work
                        </th>
                        <th>
                            start time
                        </th>
                        <th>
                            registration date
                        </th>
                        <th>
                            accept
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="suggestion" items="${suggestions}">
                        <tr>
                            <td>
                                    ${suggestion.suggestionStatus}
                            </td>
                            <td>
                                    ${suggestion.expert.score}
                            </td>
                            <td>
                                    ${suggestion.expert.firstName} ${suggestion.expert.lastName}
                            </td>
                            <td>
                                    ${suggestion.suggestedPrice}
                            </td>
                            <td>
                                    ${suggestion.durationOfWork}
                            </td>
                            <td>
                                    ${suggestion.startTime}
                            </td>
                            <td>
                                    ${suggestion.registrationDate}
                            </td>
                            <td>
                                <c:if test="${suggestion.order.orderStatus.toString() eq 'WAITING_FOR_SPECIALIST_SELECTION'}">
                                    <a href="/customer/accept_suggestion/${suggestion.identity}">click me</a>
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