<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <c:choose>
        <c:when test="${sessionScope.userLogged.role == 1}">
            <jsp:include page="clientPage.jsp" />
        </c:when>
        <c:when test="${sessionScope.userLogged.role == 3}">
            <jsp:include page="adminPage.jsp" />
        </c:when>
        <c:when test="${sessionScope.userLogged.role == 2}">
            <jsp:include page="companyPage.jsp" />
        </c:when>
        <c:otherwise>
            <header class="header">
                <a href="login" class="button">Login</a>
            </header>
        
            <section class="sidebar">
                        <div class="wrapper">
                            <h1 class="h1">Welcome to Bike Rental!</h1>
                        </div>                    
            </section>
        
            <main class="main">
                <div class="card">
                    <div class="wrapper"> 
                        <div class="search-bar">
                            <form action="company" method="get">
                                <input type="text" id="city" name="city" value="${param.city}" placeholder="Filter by city">
                                <input type="hidden" name="action" value="listByCity">
                                <button type="submit">Filter</button>
                            </form>
                        </div>
                    </div>
                    
        
                    <div class="table-container">
                        <table>
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
                    </div>
                </div>
            </main>
        </c:otherwise>
    </c:choose>    
</body>
