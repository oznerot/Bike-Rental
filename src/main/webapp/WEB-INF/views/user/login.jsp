<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <h1><fmt:message key="page.label" /></h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form action="index.jsp" method="post">
            <table>
                <tr>
                    <th><fmt:message key="user.email" /></th>
                    <td>
                        <input type="text" name="username" value="${param.username}"/>
                    </td>
                </tr>
                <tr>
                    <th><fmt:message key="user.password" /></th>
                    <td>
                        <input type="password" name="password"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="bOK" value="Login">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</fmt:bundle>
</html>