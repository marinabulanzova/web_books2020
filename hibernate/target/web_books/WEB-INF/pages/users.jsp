<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<title>Клиенты</title>
<body>
<h2>Таблица клиентов</h2><br>
<table id="table" border="1px" >
    <tr>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>e_mail</th>
        <th>номер телефона</th>
        <th>адрес</th>
        <tr><tr>

    </tr>
    <c:forEach items="${UsersList}" var = "user">
        <tr>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${user.first_name}"/></td>
            <td><c:out value="${user.e_mail}"/></td>
            <td><c:out value="${user.phone_number}"/></td>
            <td><c:out value="${user.address}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>