<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="login.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
    <div class="wrapper">
        <form action="login" method="post">
            <h1>Login</h1>
            <div class="input-box">
                <input type="text" id="email" name="email" value="${param.email}" placeholder="Email"
                required>
                <i class='bx bxs-user'></i>
            </div>

            <div class="input-box">
                <input type="password" if="password" name="password" value="${param.password}"placeholder="Password"
                required>
                <i class='bx bxs-lock-alt' ></i>
            </div>

            <div class="remember-forgot">
                <label>
                    <input type="checkbox">Remember me
                </label>
                <a href="#">Forgot password?</a>
            </div>

            <button type="submit" class="button">Login</button>

            <div class="register-link">
                <p>
                    Don't have an account?
                    <a href="client?action=register">Register as Client</a>
                    <a href="company?action=register">Register as Company</a>
                </p>
            </div>
        </form>
    </div>
</body>