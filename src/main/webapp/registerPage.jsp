<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <meta charset="UTF-8">
    <title>Register</title>
</head>

<body>
    <div align="center">
        <form action="${not empty requestScope.client ? 'client' : 'company'}" method="post">
            <input type="hidden" name="action" value="${requestScope.edit ? 'edit' : 'add'}" />

            <c:choose>
                <c:when test="${not empty requestScope.client}">
                    <jsp:include page="registerClientPage.jsp" />
                </c:when>
                <c:when test="${not empty requestScope.company}">
                    <jsp:include page="registerCompanyPage.jsp" />
                </c:when>
                <c:otherwise>
                    <p>No client or company data available.</p>
                </c:otherwise>
            </c:choose>

            <!-- Only display the submit button if we are in 'edit' mode -->
             <c:choose>
                <c:when test="${not empty requestScope.client or not empty requestScope.company}">
                    <button type="submit">Register</button>
                </c:when>
                <c:otherwise>
                </c:otherwise>
             </c:choose>
        </form>
    </div>
</body>