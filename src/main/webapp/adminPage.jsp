<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.userLogged.role == 3}">
    <header class="header">
    </header>

    <section class="sidebar">
        <div class="wrapper">
            <h1 class="h1"> Welcome ${sessionScope.userLogged.name}!</h1>
        </div>
        <div class="sidebar-options">
            <div class="options">
                <form action="client" method="get">
                    <input type="hidden" name="action" value="register">
                    <button type="submit">Add Client</button>
                </form>
                <form action="client" method="get">
                    <input type="hidden" name="action" value="list">
                    <button type="submit">List Clients</button>
                </form>
                <form action="company" method="get">
                    <input type="hidden" name="action" value="register">
                    <button type="submit">Add Companies</button>
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
                        <input type="hidden" name="action" value="listByCity">
                        <button type="submit">Filter</button>
                    </form>
                </div>
            </div>
            

            <div class="table-container">
                <c:choose>
                    <c:when test="${not empty requestScope.clientsList and sessionScope.userLogged.role == 3}">
                        <!--Clients Table-->
                        <table>
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Options</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="client" items="${requestScope.clientsList}">
                                    <tr>
                                        <td>${client.name}</td>
                                        <td>${client.email}</td>
                                        <td>${client.phone}</td>
                                        <td>
                                            <form action="client" method="post" style="display: inline;">
                                                <input type="hidden" name="action" value="register">
                                                <input type="hidden" name="id" value="${client.UUID}">
                                                <button type="submit" class="button edit-button">Edit</button>
                                            </form>

                                            <form action="client" method="post" style="display: inline;">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="${client.UUID}">
                                                <button type="submit" class="button delete-button">Delete</button>
                                            </form>
                                        </td>
                                    </tr>
                                    
                                </c:forEach>
                                <c:if test="${empty clientsList}">
                                    <tr>
                                        <td colspan="3">No client available.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <!--Companies Table-->
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
                                            <form action="company" method="post" style="display: inline;">
                                                <input type="hidden" name="action" value="register">
                                                <input type="hidden" name="id" value="${company.UUID}">
                                                <button type="submit" class="button edit-button">Edit</button>
                                            </form>

                                            <form action="company" method="post" style="display: inline;">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="${company.UUID}">
                                                <button type="submit" class="button delete-button">Delete</button>
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
