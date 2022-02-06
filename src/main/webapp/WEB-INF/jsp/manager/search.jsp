<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>search and filter users</title>
    <link rel="stylesheet" href="../../../resources/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../resources/static/css/dashboard_style.css">
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
                       href="${pageContext.request.contextPath}/portal/admin/login">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br><br>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="col-12">
        <form:form action="doFilter" modelAttribute="filterData" method="post">
            <table class="table table-bordered table-striped text-dark">
                <thead>
                <tr>
                    <th colspan="9" style="text-align: center; border: none">
                        Filter Users
                    </th>
                </tr>
                </thead>
                <tr>
                    <th>
                        <label>first name</label>
                    </th>
                    <td>
                        <label>
                            <form:input path="firstName"/>
                        </label>
                    </td>
                    <th>
                        <label>last name</label>
                    </th>
                    <td>
                        <label>
                            <form:input path="lastName"/>
                        </label>
                    </td>
                    <th>
                        <label>email</label>
                    </th>
                    <td>
                        <label>
                            <form:input path="email"/>
                        </label>
                    </td>
                    <th>
                        <label>role</label>
                    </th>
                    <td>
                        <label>
                            customer <form:radiobutton id="customer" value="CUSTOMER" path="userRole"
                                                       onclick="customerFunction()"/>
                            expert <form:radiobutton id="expert" value="EXPERT" path="userRole"
                                                     onclick="expertFunction()"/>
                        </label>
                    </td>
                    <td>
                        <input id="submit_input" type="submit" value="Submit" class="btn btn-block btn-primary"/>
                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</nav>
<br><br>
<c:if test="${users.size() gt 0}">
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
</c:if>
<script src="${pageContext.request.contextPath}/resources/static/js/register.js"></script>
</body>
</html>