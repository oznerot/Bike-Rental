<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <div class="main">
        <h1>Bike Rental</h1>
        <h3>Enter your login credentials</h3>
        <form action="">
            <label for="first">
                Username:
            </label>
            <input  type="text"
                    id="first"
                    name="first"
                    placeholder="Enter your Username" required>
            <label for="password">
                Password:
            </label>
            <input type="password"
                    id="password"
                    name="password"
                    placeholder="Enter your Password" required>
            <div class="wrap">
                <button type="submit"
                        onclick="solve()">
                        Submit
                    </button>
            </div>
        </form>
        <p>Not registered?
            <a href="#"
                style="text-decoration: none;">
                Create an account
            </a>
        </p>
    </div>
</body>