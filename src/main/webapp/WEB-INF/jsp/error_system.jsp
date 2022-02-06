<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="../../resources/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../resources/static/css/login_style.css">
</head>
<body>
<div id="login-card" class="card">
    <div class="card-body">
        <h2 class="text-center">ERROR</h2>
        <br>
        <div id="message">
            ${message}
        </div>
    </div>
</div>
<script src="../../resources/static/js/jquery-3.3.1.min.js"></script>
<script src="../../resources/static/js/popper.min.js"></script>
<script src="../../resources/static/js/bootstrap.min.js"></script>
</body>
</html>