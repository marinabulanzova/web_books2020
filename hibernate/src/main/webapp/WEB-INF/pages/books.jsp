<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Информация о книгах</title>

</head>

<body>
<%@ include file="header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li class="active">Информация о книгах</li>
            <c:if test="${admin == true}"> <li class="submenu"><a href="/add_books">Добавить новую книгу</a></li> </c:if>
            <c:if test="${admin == true}"> <li><a href="/users">Информация о клиентах</a></li> </c:if>
            <c:if test="${admin == true}"> <li class="submenu"><a href="/register">Добавить нового клиента</a></li> </c:if>
            <c:if test="${admin == true}"> <li><a href="/orders">Информация о заказах</a></li> </c:if>
            <c:if test="${admin == false}"> <li><a href="/basket">Корзина</a></li> </c:if>
            <c:if test="${id == null}"> <li class="submenu"><a href="/register">Регистрация</a></li> </c:if>
            <c:if test="${id == null}"> <li class="submenu"><a href="/login"> Вход</a></li> </c:if>
            <c:if test="${id != null}"> <li class="submenu"><a href="/account">Профиль</a></li> </c:if>
            <c:if test="${id != null}"> <li class="submenu"><a href="/logout">Выход</a></li> </c:if>
        </ul>
    </nav>
</aside>


<div id="heading">
    <h1>Информация о книгах</h1>
</div>

<%@ include file="books/form.jsp" %>
</body>

</html>