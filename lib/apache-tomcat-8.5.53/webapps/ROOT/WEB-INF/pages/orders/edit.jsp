<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title> Редактирование информации о заказе</title>
</head>

<body>
<%@ include file="../header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/" >Информация о книгах</a></li>
            <li class="submenu"><a href="/add_books">Добавить новую книгу</a></li>
            <li><a href="/users">Информация о клиентах </a></li>
            <li><a href="/orders">Информация о заказах </a></li>
            <li class="submenu"><a href="/logout"> Выход </a></li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Редактирование информации о заказе</h1>
</div>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все обязательные поля</p>
    </c:if>
    <form name="order_info_edit" action="/edit_done_orders" method="post">
        <label>
            Дата доставки
            <input class="medium" type="date" name="delivery_date" placeholder="Необходимо заполнить это поле" <c:if test="${delivery_date != null}"> value="${delivery_date}" </c:if>>
        </label>
        <label>
            Статус
            <input class="medium" type="text" name="status" placeholder="Необходимо заполнить это поле" <c:if test="${status != null}"> value="${status}" </c:if>>
        </label>
        <button class="edit" type="submit" <c:if test="${id != null}">name="id" value="${id}" </c:if>> Редактировать ✅</button>
    </form>
</section>
</body>

</html>