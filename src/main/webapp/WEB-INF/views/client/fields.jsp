<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table>
    <caption>
        <c:choose>
            <c:when test="${client != null}">
                <fmt:message key="clients.update" />
            </c:when>
            <c:otherwise>
                <fmt:message key="clients.create" />
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${client != null}">
        <input type="hidden" name="id" value="${client.id}" />
    </c:if>
    <tr>
        <td><label for="name"><fmt:message key="user.name" /></label></td>
        <td><input type="text" id ="name" name="name" size="45" value="${client.name}" /></td>
    </tr>
    <tr>
        <td><label for="email"><fmt:message key="user.email" /></label></td>
        <td><input type="text" id="email" name="email" size="45" value="${client.email}" /></td>
    </tr>
    <tr>
        <td><label for="password"><fmt:message key="user.password" /></label></td>
        <td><input type="password" id="password" name="password" size="45" value="${client.password}" /></td>
    </tr>
    <tr>
        <td><label for="cpf"><fmt:message key="client.CPF" /></label></td>
        <td><input type="text" id="cpf" name="cpf" size="45" value="${client.cpf}" /></td>
    </tr>
    <tr>
        <td><label for="gender"><fmt:message key="client.phone" /></label></td>
        <td><input type="text" id="phone" name="phone" size="45" value="${client.phone}" /></td>
    </tr>
    <tr></tr>
        <td><label for="gender"><fmt:message key="client.gender" /></label></td>
        <td><input type="text" id="gender" name="gender" size="45" value="${client.gender}" /></td>
    </tr>
    <tr></tr>
        <td><label for="birthday"><fmt:message key="client.birthday" /></label></td>
        <td><input type="text" id="birthday" name="birthday" size="45" value="${client.dateOfBirth}" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="<fmt:message key="save.link" />" /></td>
    </tr>
</table>