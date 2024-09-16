<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<fmt:bundle basename="message">
    <head>
        <title><fmt:message key="page.title" /></title>
    </head>
    <body>
        <div align="center">
            <c:if test="${userLogged.role == 'ADMIN'}">
                <h1>
                    <fmt:message key="companies.welcome" />
                </h1>
            </c:if>
            <h2>
                <c:if test="${userLogged.role == 'ADMIN'}">
                    <a href="/${sessionScope.contextPath}/clients">
                        <fmt:message key="clients.entity" />
                    </a>
                    &nbsp;&nbsp;&nbsp;
                </c:if>
                <c:choose>
                    <c:when test="${userLogged != null}">
                        <a href="${pageContext.request.contextPath}/logout.jsp">
                            <fmt:message key="exit.link" />
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/login">
                            Login
                        </a>
                    </c:otherwise>
                </c:choose>

                <br/>
                <br/>
                <c:if test="${userLogged.role == 'ADMIN'}">
                    <a href="/${sessionScope.contextPath}/companies/register">
                        <fmt:message key="companies.create" />
                    </a>
                </c:if>
            </h2>
            <h3><fmt:message key="companies.list" /></h3>
            <br/>
        </div>
        <div align="center">
            <form action="${pageContext.request.contextPath}/companies/list" method="get">
                <label for="city"><fmt:message key="companies.search" /></label>
                <input type="text" id="city" name="city"/>
                <button type="submit"><fmt:message key="companies.filter" /></button>
            </form>
            <c:choose>
                <c:when test="${not empty companies}">
                    <table border="1">
                        <tr>
                            <c:if test="${userLogged.role == 'ADMIN'}">
                                <th><fmt:message key="user.ID" /></th>
                            </c:if>
                            
                            <th><fmt:message key="user.name" /></th>
                            <th><fmt:message key="user.email" /></th>
        
                            <c:if test="${userLogged.role == 'ADMIN'}">
                                <th><fmt:message key="company.CNPJ" /></th>
                            </c:if>
        
                            <th><fmt:message key="company.city" /></th>
                            <c:if test="${userLogged.role == 'ADMIN'}">
                                <th><fmt:message key="actions.link" /></th>
                            </c:if>
                        </tr>
                        <c:forEach var="company" items="${requestScope.companies}">
                            <tr>
                                <c:if test="${userLogged.role == 'ADMIN'}">
                                    <td><c:out value="${company.id}" /></td>
                                </c:if>
        
                                <td><c:out value="${company.name}" /></td>
                                <td><c:out value="${company.email}" /></td>
        
                                <c:if test="${userLogged.role == 'ADMIN'}">
                                    <td><c:out value="${company.cnpj}" /></td>
                                </c:if>
        
                                <td><c:out value="${company.city}" /></td>
        
                                <c:if test="${userLogged.role == 'ADMIN'}">
                                    <td>
                                        <a href="/${sessionScope.contextPath}/companies/edit?id=${company.id}">
                                            <fmt:message key="companies.update" />
                                        </a>
                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="/${sessionScope.contextPath}/companies/delete?id=${company.id}"
                                            onclick="return confirm('Tem certeza que deseja excluir esse item?');">
                                            <fmt:message key="companies.delete" />
                                        </a>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p>Nenhuma locadora encontrada.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</fmt:bundle>
</html>