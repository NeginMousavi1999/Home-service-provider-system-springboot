<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/login_style.css"/>">
</head>
<body>
<div class="d-lg-flex half">
    <div class="bg order-1 order-md-2" style="background-image: url('/images/bg_1.jpg');"></div>
    <div class="contents order-2 order-md-1">
        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-md-7">
                    <h3>Register to <strong>Home Service Provider System</strong></h3>
                    <p class="mb-4">Thanks for choosing us ^_^</p>

                    <form:form action="doRegister" modelAttribute="registerData" method="post"
                               enctype="multipart/form-data">
                        <div class="form-group">
                            <label>register as:</label>
                            <br>
                            <label>customer</label>
                            <form:radiobutton id="customer" value="CUSTOMER" path="userRole"
                                              onclick="customerFunction()"/>
                            <label>expert</label>
                            <form:radiobutton id="expert" value="EXPERT" path="userRole" onclick="expertFunction()"/>
                            <br>
                            <form:errors path="userRole" cssStyle="color: red"/>
                        </div>

                        <div class="form-group">
                            <form:input path="firstName" placeholder='First Name'/>
                            <br>
                            <form:errors path="firstName" cssStyle="color: red"/>
                        </div>

                        <div class="form-group">
                            <form:input path="lastName" placeholder='Last Name'/>
                            <br>
                            <form:errors path="lastName" cssStyle="color: red"/>
                        </div>

                        <div class="form-group">
                            <form:input path="email" placeholder='Email'/>
                            <br>
                            <form:errors path="email" cssStyle="color: red"/>
                        </div>

                        <div class="form-group mb-3">
                            <form:password path="password" placeholder='password'/>
                            <br>
                            <form:errors path="password" cssStyle="color: red"/>
                        </div>

                        <div id="expert_info" class="form-group mb-3" style="display: none">
                            <input id="formFileSm" class="form-control form-control-sm" type="file" name="file"
                                   accept="image/*" onchange="return fileValidation()">
                        </div>
                        <p class="mb-4">Already have an account? <a
                                href="${pageContext.request.contextPath}/">Login!</a></p>
                        <input type="submit" value="Register" class="btn btn-block btn-primary"/>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/js/jquery-3.3.1.min.js"/>"></script>
<script src="<c:url value="/js/popper.min.js"/>"></script>
<script src="<c:url value="/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/js/register.js"/>"></script>
</body>
</html>