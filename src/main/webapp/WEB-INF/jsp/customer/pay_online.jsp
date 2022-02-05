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
                    <a class="nav-link" href="${pageContext.request.contextPath}/customer/all_orders">Back</a>
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
            <div class="col-md-12">
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <form:form action="${pageContext.request.contextPath}/customer/pay_online" method="post"
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
                                    ${paymentDto.order.finalPrice}
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

<script type="text/javascript">
    let IdealTimeOut = 100; //10 seconds
    let idleSecondsTimer = null;
    let idleSecondsCounter = 0;
    document.onclick = function () {
        idleSecondsCounter = 0;
    };
    document.onmousemove = function () {
        idleSecondsCounter = 0;
    };
    document.onkeypress = function () {
        idleSecondsCounter = 0;
    };
    idleSecondsTimer = window.setInterval(CheckIdleTime, 1000);

    function CheckIdleTime() {
        idleSecondsCounter++;
        let oPanel = document.getElementById("timeOut");
        if (oPanel) {
            oPanel.innerHTML = (IdealTimeOut - idleSecondsCounter);
        }
        if (idleSecondsCounter >= IdealTimeOut) {
            window.clearInterval(idleSecondsTimer);
            alert("Your Session has expired. Please try again.");
            window.location = "http://localhost:8080/customer/all_orders";
        }
    }
</script>
</body>
</html>