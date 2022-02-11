<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Order</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/dashboard_style.css"/>">


    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://static.neshan.org/sdk/leaflet/1.4.0/leaflet.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="https://static.neshan.org/sdk/openlayers/4.6.5/ol.css">
    <script src="https://cdn.polyfill.io/v2/polyfill.min.js?features=requestAnimationFrame,Element.prototype.classList,URL"></script>
    <script type="text/javascript" src="https://static.neshan.org/sdk/openlayers/4.6.5/ol.js"></script>
    <script src="https://static.neshan.org/sdk/leaflet/1.4.0/leaflet.js" type="text/javascript"></script>
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
        <div class="row align-items-center justify-content-center mb-5">
            <div class="col-12">
                <div id="succ_massage" style="color: green"></div>
                <div id="error_massage" style="color: red"></div>
                <form id="add-order-form">
                    <table class="table table-bordered text-dark text-center">
                        <thead>
                        <tr>
                            <th colspan="4" style="background-color: #fb771a; color: white">
                                Order information
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th colspan="2">
                                lat
                            </th>
                            <th colspan="2">
                                lng
                            </th>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <label for="lat"><input type="text" id="lat"></label>
                            </td>
                            <td colspan="2">
                                <label for="lng"><input type="text" id="lng"></label>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="4">
                                <label>choose your location</label>
                            </th>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <div class="d-flex justify-content-center">
                                    <div id="map"
                                         style="width: 600px; height: 450px; background: #eee; border: 2px solid #aaa;"></div>
                                </div>
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
                                            <label for="subServiceName"><input type="radio" value="${subservice.name}"
                                                                               id="subServiceName"/></label>
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
                                <label for="description"><textarea id="description"
                                                                   style="width: 100%"></textarea></label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Submit" class="btn btn-block btn-primary"/>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/order.js"/>"></script>
</body>
</html>