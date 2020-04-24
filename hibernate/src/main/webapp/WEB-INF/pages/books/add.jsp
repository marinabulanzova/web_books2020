<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Добавление новой книги</title>
    <meta http-equiv="content-type", content="text/html", charset="utf-8">
</head>

<body>
<%@ include file="../header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/" >Информация о книгах</a></li>
            <li class="active"><a href="/add_books">Добавить новую книгу</a></li>
            <li><a href="/users">Информация о клиентах></a></li>
            <li><a href="/orders">Информация о заказах</a></li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Добавление новой книги</h1>
</div>

<%@ include file="add_edit_section.jsp" %>
</body>
</html>