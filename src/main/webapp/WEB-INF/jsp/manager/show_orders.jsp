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
                    <th colspan="6" style="text-align: center; border: none">
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
                    <th>
                        <label>action</label>
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

                    <td>
                        <input id="submit_input" type="submit" value="Submit" class="btn btn-block btn-primary"/>
                       <%-- <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" id="bth-search"
                                        class="btn btn-primary btn-lg">Search
                                </button>
                            </div>
                        </div>--%>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</nav>
<br><br>
<div id="feedback"></div>
<%--<c:if test="${users.size() gt 0}">
    <div class="contents order-2 order-md-1">
        <div class="container">
            <div class="row align-items-center justify-content-center mb-5">
                <div class="col-12">
                    <table class="table table-bordered table-striped text-dark">
                        <thead>
                        <tr>
                            <th colspan="7" style="text-align: center">
                                result
                            </th>
                        </tr>
                        <tr>
                            <th>
                                first name
                            </th>
                            <th>
                                last name
                            </th>
                            <th>
                                email
                            </th>
                            <th>
                                role
                            </th>
                            <th>
                                registration date
                            </th>
                            <th>
                                status
                            </th>
                            <th>
                                actions
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>
                                        ${user.firstName}
                                </td>
                                <td>
                                        ${user.lastName}
                                </td>
                                <td>
                                        ${user.email}
                                </td>
                                <td>
                                        ${user.userRole}
                                </td>
                                <td>
                                        ${user.registrationDate}
                                </td>
                                <td>
                                        ${user.userStatus}
                                </td>

                                <td>
                                    <c:if test="${user.userStatus.toString() eq 'WAITING' and user.userRole.toString() eq 'EXPERT'}">
                                        <a href="/portal/admin/dashboard/confirm/${user.identity}">confirm user</a>
                                    </c:if>
                                    <c:if test="${user.userStatus.toString() eq 'CONFIRMED' and user.userRole.toString() eq 'EXPERT'}">
                                        <a href="/portal/admin/dashboard/show_subservices_for_expert/${user.identity}">add
                                            sub service</a>
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
</c:if>--%>
<script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/manager.js"/>"></script>
</body>
</html>