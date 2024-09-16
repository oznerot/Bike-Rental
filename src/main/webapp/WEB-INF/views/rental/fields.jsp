<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table border="1">
    <tr>
        <th></th>
    </tr>
    <tr>
        <td><label for="company"><fmt:message key="company.name" /></label></td>
        <td>
            <input list="companies" id="company" name="companyId" required>
            <datalist id="companies">
                <c:forEach var="company" items="${companies}">
                    <option value="${company.id}" data-name="${company.name}">${company.name}</option>
                </c:forEach>
            </datalist>
        </td>
    </tr>
    <tr>
        <td><label for="dateHour"><fmt:message key="rental.date" /></label></td>
        <td><input type="datetime-local" id="dateHour" name="dateHour" required></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
    </tr>
</table>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        var input = document.getElementById('company');
        
        input.addEventListener('change', function() {
            var value = input.value;
            var options = document.querySelectorAll('#companies option');
            
            options.forEach(function(option) {
                if (option.value === value) {
                    input.value = option.getAttribute('data-name');
                    input.setAttribute('data-id', value);
                }
            });
        });

        var form = input.closest('form');
        if (form) {
            form.addEventListener('submit', function() {
                var id = input.getAttribute('data-id');
                input.value = id;
            });
        }
    });
</script>