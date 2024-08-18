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
    <c:if test="${sessionScope.userLogged.role == 1}">
        <header class="header">
        </header>

        <section class="sidebar">
            <div class="wrapper">
                <h1 class="h1"> Welcome ${sessionScope.userLogged.name}!</h1>
            </div>
            <div class="sidebar-options">
                <div class="options">
                    <form action="rental" method="get">
                        <input type="hidden" name="action" value="rent">
                        <button type="submit">Rent a Bike</button>
                    </form>
                    <form action="rentals" method="get">
                        <input type="hidden" name="action" value="listRent">
                        <button type="submit">My Rentals</button>
                    </form>
                    <form action="company" method="get">
                        <input type="hidden" name="action" value="listCompanies">
                        <button type="submit">List Companies</button>
                    </form>

                    <form action="#" method="get">
                        <input type="hidden" name="action" value="getInfo">
                        <button type="submit">About Me</button>
                    </form>
                </div>
                <div class="exit">
                    <a href="login" class="exit-button">Exit</a>
                </div>
            </div>       
            
        </section>

        <main class="main">
            <div class="card">
                <div class="wrapper"> 
                    <div class="search-bar">
                        <form action="company" method="get">
                            <input type="text" id="city" name="city" value="${param.city}" placeholder="Filter by city">
                            <input type="hidden" name="action" value="listCompaniesByCity">
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
    </c:if>
</body>
