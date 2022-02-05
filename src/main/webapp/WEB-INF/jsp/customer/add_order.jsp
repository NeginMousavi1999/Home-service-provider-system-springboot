<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Order</title>
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
        <div class="row align-items-center justify-content-center mb-5">
            <div class="col-12">
                <div style="color: green">${succ_massage}</div>
                <div style="color: red">${error_massage}</div>
                <form:form action="add_new_order" modelAttribute="order" method="post">
                    <table class="table table-bordered text-dark text-center">
                        <thead>
                        <tr>
                            <th colspan="4" style="background-color: #fb771a; color: white">
                                Adding Order Form
                            </th>
                        </tr>
                        <tr>
                            <th colspan="4" style="background-color: #fb771a; color: white">
                                your Address information
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>
                                <label>country</label>
                            </th>
                            <td>
                                <label>
                                    <form:input path="country"/>
                                </label>
                            </td>
                            <th>
                                <label>city</label>
                            </th>
                            <td>
                                <label>
                                    <form:input path="city"/>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <label>state</label>
                            </th>
                            <td>
                                <label>
                                    <form:input path="state"/>
                                </label>
                            </td>
                            <th>
                                <label>Postal Code</label>
                            </th>
                            <td>
                                <label>
                                    <form:input path="postalCode"/>
                                </label>
                                <form:errors path="postalCode" cssStyle="color: red"/>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="4" style="background-color: #fb771a; color: white">
                                sub service
                            </th>
                        </tr>
                        <tr>
                            <th>
                                select
                            </th>
                            <th>
                                name
                            </th>
                            <th>
                                description
                            </th>
                            <th>
                                cost
                            </th>
                        </tr>
                        <div class="form-group first">
                            <c:forEach var="service" items="${set}">
                                <tr>
                                    <th style="background-color: white" colspan="4">
                                        service
                                    </th>
                                </tr>
                                <tr>
                                    <td colspan="4" style="background-color:rgba(0, 0, 0, 0.05)">
                                            ${service.name}
                                    </td>
                                </tr>
                                <c:forEach var="subservice" items="${service.subServices}">
                                    <tr style="background-color:rgba(0, 0, 0, 0.05)">
                                        <td>
                                            <form:radiobutton value="${subservice.name}"
                                                              path="subServiceName"/>
                                        </td>
                                        <td>
                                                ${subservice.name}
                                        </td>
                                        <td>
                                                ${subservice.description}
                                        </td>
                                        <td>
                                                ${subservice.cost}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </div>

                        <tr>
                            <th colspan="4" style="background-color: #fb771a; color: white">
                                your Description
                            </th>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <form:textarea path="description" cssStyle="width: 100%"/>
                                <form:errors path="description" cssStyle="color: red"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Submit" class="btn btn-block btn-primary"/>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>