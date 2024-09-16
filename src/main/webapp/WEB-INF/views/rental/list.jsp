<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<fmt:bundle basename="message">
    <head>
        <meta charset="UTF-8">
        <title><fmt:message key="page.title" /></title>
    </head>
    <body>
        <div align="center">
            <h1>
                <fmt:message key="rentals.welcome" />
            </h1>
            <h2>
                <c:if test="${userLogged.role == 'CLIENT'}">
                    <a href="/${sessionScope.contextPath}/companies/list"><fmt:message key="companies.list" /></a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/${sessionScope.contextPath}/rentals/register">
                        <fmt:message key="rentals.create" />
                    </a>
                    &nbsp;&nbsp;&nbsp;
                </c:if>
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
                </a>
            </h2>
            <br/>
            <h3><fmt:message key="rentals.list" /></h3>
            <br/>
        </div>

        <div align="center">
            <table border="1">
                <caption></caption>
                <tr>
                    <th><fmt:message key="rental.ID" /></th>
                    <th><fmt:message key="rental.date" /></th>
                    <th><fmt:message key="company.CNPJ" /></th>
                    <th><fmt:message key="client.CPF" /></th>
                </tr>
                <c:forEach var="rental" items="${requestScope.rentals}">
                    <tr>
                        <td>${rental.id}</td>
                        <td>${rental.dateHour}</td>
                        <td>${rental.company.cnpj}</td>
                        <td>${rental.client.cpf}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</fmt:bundle>
</html>