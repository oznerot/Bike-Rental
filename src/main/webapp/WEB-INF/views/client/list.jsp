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
            <h1>
                <fmt:message key="clients.welcome" />
            </h1>
            <h2>
                <a href="/${sessionScope.contextPath}/companies">
                    <fmt:message key="companies.entity" />
                </a>
                &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/logout.jsp">
                    <fmt:message key="exit.link" />
                </a>
                <br/>
                <br/>
                <a href="/${sessionScope.contextPath}/clients/register">
                    <fmt:message key="clients.create" />
                </a>
            </h2>
            <h3><fmt:message key="clients.list" /></h3>
            <br/>
        </div>
        <div align="center">
            <table border="1">
                <tr>
                    <th><fmt:message key="user.ID" /></th>
                    <th><fmt:message key="user.name" /></th>
                    <th><fmt:message key="user.email" /></th>
                    <th><fmt:message key="client.CPF" /></th>
                    <th><fmt:message key="client.phone" /></th>
                    <th><fmt:message key="client.gender" /></th>
                    <th><fmt:message key="client.birthday" /></th>
                    <th><fmt:message key="actions.link" /></th>
                </tr>
                <c:forEach var="client" items="${requestScope.clients}">
                    <tr>
                        <td><c:out value="${client.id}" /></td>
                        <td><c:out value="${client.name}" /></td>
                        <td><c:out value="${client.email}" /></td>
                        <td><c:out value="${client.cpf}" /></td>
                        <td><c:out value="${client.phone}" /></td>
                        <td><c:out value="${client.gender}" /></td>
                        <td><c:out value="${client.dateOfBirth}" /></td>
                        <td>
                            <a href="/${sessionScope.contextPath}/clients/edit?id=${client.id}">
                                <fmt:message key="clients.update" />
                            </a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/${sessionScope.contextPath}/clients/delete?id=${client.id}"
                                onclick="return confirm('<fmt:message key="confirm.link" />');">
                                <fmt:message key="clients.delete" />
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</fmt:bundle>
</html>