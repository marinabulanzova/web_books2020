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
        <th></th>
        <th>Фамилия</th>
        <th>Имя</th>
        <th>e_mail</th>
        <th>номер телефона</th>
        <th>адрес</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach items="${UsersList}" var = "user">
        <tr>
            <td><c:out value="${user.surname}"/></td>
            <td><c:out value="${cust.first_name}"/></td>
            <td><c:out value="${cust.e_mail}"/></td>
            <td><c:out value="${cust.phone_number}"/></td>
            <td><c:out value="${cust.address}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>