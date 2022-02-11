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
    <style>
        #ten-countdown {
            /*border: 5px solid #fb771a;*/
            display: inline;
            padding: 5px;
            color: #fb771a;
            font-family: Verdana, sans-serif, Arial;
            font-size: 30px;
            font-weight: bold;
            text-decoration: none;
        }
    </style>
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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="col-12 d-flex justify-content-center">
        <div>
            <div id="ten-countdown"></div>
        </div>
    </div>
</nav>
<br><br>
<div class="contents order-2 order-md-1">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-12">
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <form:form action="${pageContext.request.contextPath}/customer/BankPaymentGateway" method="post"
                           modelAttribute="paymentDto">
                    <table class="table table-bordered table-striped text-dark text-center">
                        <thead>
                        <tr>
                            <th colspan="5">
                                Bank
                            </th>
                        </tr>
                        <tr>
                            <th>
                                cost
                            </th>
                            <th>
                                card number
                            </th>
                            <th>
                                cvv2
                            </th>
                            <th>
                                expire
                            </th>
                            <th>
                                password
                            </th>
                        </tr>
                        </thead>
                        <tr>
                            <td>
                                    ${paymentDto.cost}
                            </td>
                            <td>
                                <label>
                                    <form:input type="text" path="cardNumber"/>
                                    <form:errors path="cardNumber" cssStyle="color: red"/>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="text"/>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="date"/>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <input type="password"/>
                                </label>
                            </td>
                        </tr>

                    </table>
                    <input type="submit" value="Pay" class="btn btn-block btn-primary"/>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/pay_online.js"/>"></script>
</body>
</html>