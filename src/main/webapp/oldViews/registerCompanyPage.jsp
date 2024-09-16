<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>

<body>
    <h1>Company Register</h1>
        <input type="hidden" name="id" value="${requestScope.company.UUID}">
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
        <div>
            <input type="text" id="cnpj" name="cnpj" value="${param.cnpj}" placeholder="CNPJ" required>
        </div>
        <div>
            <input type="text" id="city" name="city" value="${param.city}" placeholder="City" required>
        </div>
</body>