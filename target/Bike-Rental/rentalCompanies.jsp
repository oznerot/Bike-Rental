<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
    <meta charset="UTF-8">
    <title>Bike Rental</title>
</head>
<body>
	<h1>Rental Companies</h1>
	<table border="1" style="width: 400px; border: 1px solid black">
		<thead>
			<tr>
				<th>Name</th>
				<th>Email</th>
				<th>City</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="company" items="${requestScope.companiesList}">
				<tr>
					<td>${company.name}</td>
					<td>${company.email}</td>
					<td>${company.city}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty companiesList}">
				<tr>
					<td colspan="3">No rental companies available.</td>
				</tr>
			</c:if>
		</tbody>	
	</table>
</body>