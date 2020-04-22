<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Результаты поиска</title>
</head>

<body>
<%@ include file="../header.jsp" %>
<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/users">Информация о клиентах</a></li>
            <li class="active">Информация о книгах</li>
            <li><a href="/orders">Информация о заказах</a></li>
            <li class="submenu"><a href="/books/add">Добавить новую книгу</a></li>
            <li class="submenu"><a href="/users/add">Регистрация</a></li>
            <li class="submenu"><a href="/users/login">Вход</a></li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Результаты поиска книг</h1>
</div>
<%@ include file="form.jsp" %>
</body>

</html>