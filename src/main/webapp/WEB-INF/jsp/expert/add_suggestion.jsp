<%--@elvariable id="error_massage" type="antlr"--%>
<%--@elvariable id="succ_massage" type="antlr"--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Suggestion</title>
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
                <form:form action="/expert/add_new_suggestion" method="post" modelAttribute="suggestion_request">
                    <table class="table table-bordered table-striped text-dark text-center">
                        <thead>
                        <tr>
                            <th colspan="3">
                                suggestion form
                            </th>
                        </tr>
                        <tr>
                            <th>
                                to be done date
                            </th>
                            <th>
                                suggested price
                            </th>
                            <th>
                                duration of work
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <label>
                                    <form:input path="startTime" type="date" name="date"/>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <form:input path="suggestedPrice" type="number" name="suggestedPrice"/>
                                </label>
                            </td>
                            <td>
                                <label>
                                    <form:input path="durationOfWork" type="number" name="durationOfWork"/>
                                </label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <input type="submit" value="Add my suggestion" class="btn btn-block btn-primary"/>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>