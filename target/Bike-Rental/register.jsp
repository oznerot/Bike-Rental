<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" href="login.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
    <form action="add" method="post">
        <div>
            <label>
                <input type="radio" name="userType" value="client" ${param.userType == 'client' ? 'checked' : ''}>
                Client
            </label>
            <label>
                <input type="radio" name="userType" value="company" ${param.userType == 'company' ? 'checked' : ''}>
                Company
            </label>
        </div>

        <h1>Register</h1>
        <div>
            <input type="text" id = "name" name="name" value="${param.name}" placeholder="Your name" required>
        </div>
        <div>
            <input type="text" id="email" name="email" value="${param.email}" placeholder="Email"
            required>
        </div>

        <div>
            <input type="password" if="password" name="password" value="${param.password}"placeholder="Password"
            required>
        </div>

        <!-- Conditional fields based on userType -->
        <c:choose>
            <c:when test="${param.userType == 'client'}">
                <!-- Fields specific to client registration -->
                <div>
                    <input type="text" id="phone" name="phone" value="${param.phone}" placeholder="Phone number" required>
                </div>
            </c:when>
            <c:when test="${param.userType == 'company'}">
                <!-- Fields specific to company registration -->
                <div>
                    <input type="text" id="companyName" name="companyName" value="${param.companyName}" placeholder="Company name" required>
                </div>
                <div>
                    <input type="text" id="city" name="city" value="${param.city}" placeholder="City" required>
                </div>
            </c:when>
            <c:otherwise>
                <!-- Default fields or message if no type is selected -->
                <p>Please select a type.</p>
            </c:otherwise>
        </c:choose>
        
        <button type="submit" class="button">Login</button>

    </form>
</body>