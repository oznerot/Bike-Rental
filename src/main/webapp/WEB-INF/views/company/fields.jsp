<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table>
    <caption>
        <c:choose>
            <c:when test="${company != null}">
                <fmt:message key="companies.update" />
            </c:when>
            <c:otherwise>
                <fmt:message key="companies.create" />
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${company != null}">
        <input type="hidden" name="id" value="${company.id}" />
    </c:if>
    <tr>
        <td><label for="name"><fmt:message key="user.name" /></label></td>
        <td><input type="text" id ="name" name="name" size="45" value="${company.name}" /></td>
    </tr>
    <tr>
        <td><label for="email"><fmt:message key="user.email" /></label></td>
        <td><input type="text" id="email" name="email" size="45" value="${company.email}" /></td>
    </tr>
    <tr>
        <td><label for="password"><fmt:message key="user.password" /></label></td>
        <td><input type="password" id="password" name="password" size="45" value="${company.password}" /></td>
    </tr>
    <tr>
        <td><label for="cnpj"><fmt:message key="company.CNPJ" /></label></td>
        <td><input type="text" id="cnpj" name="cnpj" size="45" value="${company.cnpj}" /></td>
    </tr>
    <tr>
        <td><label for="city"><fmt:message key="company.city" /></label></td>
        <td><input type="text" id="city" name="city" size="45" value="${company.city}" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="<fmt:message key="salve.link" />" /></td>
    </tr>
</table>