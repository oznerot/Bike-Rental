<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>

<body>
    <h1>Rental Register</h1>
    <input type="hidden" name="clientId" value="${requestScope.clientId}">
    <input type="hidden" name="companyId" value="${requestScope.companyId}">
    <div>
        <label for="birthday">Choose a date:</label>
        <input type="date" id="rentalDate" name="rentalDate">
    </div> 

    <label for="rentalTime">Choose a rental time:</label>
    <select id="rentalTime" name="rentalTime">
        <c:forEach var="time" items="${rentalTimes}">
            <option value="${time.hour}">${time.hour}</option>
        </c:forEach>
    </select>  
</body>