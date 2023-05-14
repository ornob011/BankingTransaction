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
    <title>Register</title>
</head>
<body style="background-color: #666666;">

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">


            <form:form action="/register" class="login100-form validate-form" modelAttribute="registerUser">
                        <span class="login100-form-title p-b-43">
						Register to continue
					</span>

                <!-- Display Message -->
                <c:if test="${requestScope.passwordMisMatch != null}">
                    <div class="alert alert-danger text-center border border-danger">
                        <b>${requestScope.passwordMisMatch}</b>
                    </div>
                </c:if>
                <!-- End Of Display Message -->

                <!-- Display Message -->
                <c:if test="${requestScope.success != null}">
                    <div class="alert alert-success text-center border border-success">
                        <b>${requestScope.success}</b>
                    </div>
                </c:if>
                <!-- End Of Display Message -->


                <div class="wrap-input100 validate-input" data-validate="First Name is required">
                    <form:input class="input100" type="text" path="first_name" name="first_name"/>
                    <form:errors path="first_name" class="text-white bg-danger"/>
                    <span class="focus-input100"></span>
                    <span class="label-input100">First name </span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Second Name is required">
                    <form:input path="last_name" class="input100" type="text" name="last_name"/>
                    <form:errors path="last_name" class="text-white bg-danger"/>
                    <span class="focus-input100"></span>
                    <span class="label-input100">Last name </span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Valid email is required: ex@abc.xyz">
                    <form:input type="email" path="email" class="input100" name="email"/>
                    <form:errors path="email" class="text-white bg-danger"/>
                    <span class="focus-input100"></span>
                    <span class="label-input100">Email</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Password is required">
                    <form:input type="password" path="password" class="input100" name="pass"/>
                    <form:errors path="password" class="text-white bg-danger"/>
                    <span class="focus-input100"></span>
                    <span class="label-input100">Password</span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Repeat Password is required">
                    <input type="password" name="confirm_password" class="input100"/>
                    <small class="text-white bg-danger">${confirm_pass}</small>
                    <span class="focus-input100"></span>
                    <span class="label-input100">Repeat Password</span>
                </div>

                <div class="flex-sb-m w-full p-t-3 p-b-32">
                    <div class="contact100-form-checkbox">
                        <p class="text-dark">
                            Already have an account?
                        </p>
                    </div>

                    <div>
                        <a href="/login" class="txt1 text-info">
                            Login
                        </a>
                    </div>
                </div>


                <div class="container-login100-form-btn">
                    <button class="login100-form-btn">Sign Up</button>
                </div>
            </form:form>

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
