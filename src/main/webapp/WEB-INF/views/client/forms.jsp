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
            <h1><fmt:message key="clients.welcome" /></h1>
            <h2>
                <a href="${sessionScope.contextPath}/companies"><fmt:message key="companies.entity" /></a>
                &nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/logout.jsp"><fmt:message key="exit.link" /></a>
                <br/>
                <br/>
                <a href="list"><fmt:message key="clients.list" /></a>
            </h2>
        </div>
        <div align="center">
            <c:choose>
                <c:when test="${client != null}">
                    <form action="update" method="post">
                        <%@include file="fields.jsp"%>
                    </form>
                </c:when>
                <c:otherwise>
                    <form action="add" method="post">
                        <%@include file="fields.jsp"%>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
		<c:if test="${!empty requestScope.mensagens}">
			<ul class="erro">
				<c:forEach items="${requestScope.mensagens}" var="mensagem">
					<li>${mensagem}</li>
				</c:forEach>
			</ul>
		</c:if>
    </body>
</fmt:bundle>
</html>