<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>

<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>

<body>
    <h1>Client Register</h1>
    <input type="hidden" name="id" value="${requestScope.client.UUID}">
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
    <!-- Fields specific to client registration -->
    <div>
        <input type="text" id="cpf" name="cpf" value="${param.cpf}" placeholder="Your CPF" required>
    </div>
    <div>
        <input type="text" id="phone" name="phone" value="${param.phone}" placeholder="Your Phone Number" required>
    </div>
    <div>
        <label>
            <input type="radio" id="male" name="gender" value="Male" ${param.gender == 'Male' ? 'checked' : ''}>
            Male
        </label>
        <label>
            <input type="radio" id="female" name="gender" value="Female" ${param.gender == 'Female' ? 'checked' : ''}>
            Female
        </label>
        <label>
            <input type="radio" id="other" name="gender" value="Other" ${param.gender == 'Other' ? 'checked' : ''}>
            Other
        </label>
    </div>
    <div>
        <label for="birthday">Select your birthday:</label>
        <input type="date" id="birthday" name="birthday">
    </div>      
</body>