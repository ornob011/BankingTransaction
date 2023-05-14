<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="css/fontawesome/css/all.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <title>Login</title>
</head>
<body style="background-color: #666666;">
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">

            <form action="/login" method="POST" class="login100-form validate-form">
                            <span class="login100-form-title p-b-43">
                            Login to continue
                        </span>
                <!-- Display Message -->
                <c:if test="${requestScope.success != null}">
                    <div class=" alert alert-success text-center border border-success
            ">
                        <b>${requestScope.success}</b>
                    </div>
                </c:if>
                <!-- End Of Display Message -->

                <!-- Display Message -->
                <c:if test="${requestScope.error != null}">
                    <div class="alert alert-danger text-center border border-danger">
                        <b>${requestScope.error}</b>
                    </div>
                </c:if>
                <!-- End Of Display Message -->

                <!-- Display Message -->
                <c:if test="${logged_out != null}">
                    <div class="alert alert-info text-center border border-info">
                        <b>${logged_out}</b>
                    </div>
                </c:if>

                <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                    <input class="input100" type="email" name="email">
                    <span class="focus-input100"></span>
                    <span class="label-input100">Email</span>
                </div>


                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <input class="input100" type="password" name="password">
                    <span class="focus-input100"></span>
                    <span class="label-input100">Password</span>
                </div>

                <div class="form-group col">
                    <input type="hidden" name="_token" value="${token}"/>
                </div>

                <div class="flex-sb-m w-full p-t-3 p-b-32">
                    <div class="contact100-form-checkbox">
                        <p class="text-dark">
                            Don't have an account?
                        </p>
                    </div>

                    <div>
                        <a href="/register" class="txt1 text-info">
                            Sign Up
                        </a>
                    </div>
                </div>


                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">Login</button>
                </div>
            </form>

            <div class="login100-more" style="background-image: url('images/banks.webp');">
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="css/bootstrap/js/popper.js"></script>
<script src="css/bootstrap/js/bootstrap.min.js"></script>
<script src="js/login.js"></script>

</body>
</html>