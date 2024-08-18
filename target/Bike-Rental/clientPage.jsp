<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
                        <input type="hidden" name="action" value="register">
                        <button type="submit">Rent a Bike</button>
                    </form>
                    <form action="rental" method="get">
                        <input type="hidden" name="clientId" value="${sessionScope.userLogged.UUID}">
                        <input type="hidden" name="action" value="clientRentals">
                        <button type="submit">My Rentals</button>
                    </form>
                    <form action="company" method="get">
                        <input type="hidden" name="action" value="list">
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
                    <c:choose>
                        <c:when test="${not empty requestScope.rentalsList and sessionScope.userLogged.role == 1}">
                            <table>
                                <t:head>
                                    <tr>
                                        <th>Company</th>
                                        <th>Email</th>
                                        <th>Date</th>
                                    </tr>
                                </t:head>
                                <t:body>
                                    <c:forEach var="rental" items="${requestScope.rentalsList}">
                                        <tr>
                                            <td>${rental.companyName}</td>
                                            <td>${rental.companyEmail}</td>
                                            <td>${rental.dateHour}</td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty rentalsList}">
                                        <tr>
                                            <td colspan="3">No rentals made.</td>
                                        </tr>
                                    </c:if>
                                </t:body>
                            </table>
                        </c:when>
                        <c:otherwise>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>City</th>
                                        <th>Options</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="company" items="${requestScope.companiesList}">
                                        <tr>
                                            <td>${company.name}</td>
                                            <td>${company.email}</td>
                                            <td>${company.city}</td>
                                            <td>
                                                <form action="rental" method="get" style="display: inline;">
                                                    <input type="hidden" name="action" value="register">
                                                    <input type="hidden" name="clientId" value="${sessionScope.userLogged.UUID}">
                                                    <input type="hidden" name="companyId" value="${company.UUID}">
                                                    <button type="submit">Rent</button>
                                                </form>
                                            </td>
                                        </tr>
                                        
                                    </c:forEach>
                                    <c:if test="${empty companiesList}">
                                        <tr>
                                            <td colspan="3">No rental companies available.</td>
                                        </tr>
                                    </c:if>
                                </tbody>	
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </main>
    </c:if>
</body>
