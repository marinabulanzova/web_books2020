<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Личная информация</title>
</head>

<body>
<%@ include file="../header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li class="active">Профиль</li>
            <li><a href="/my_orders">Мои заказы</a></li>
            <li><a href="/basket">Корзина</a></li>
            <li class="submenu"><a href="/logout">Выход</a></li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h2>Личная информация</h2>
    <h3> Фамилия: ${user.surname} </h3>
    <h3>Имя: ${user.first_name} </h3>
    <h3>Отчество: ${user.patronymic} </h3>
    <h3>Адрес: ${user.address} </h3>
    <h3>e_mail: ${user.e_mail}</h3>
    <h3>Номер телефона: ${user.phone_number} </h3>
    <form name="edit_account" id="edit_account" action="/edit_account" method="get">
        <button class="edit" title="Редактировать профиль" type="submit" name="id" value="${user.id_user}"> Редактировать профиль 📝 </button>
    </form>

    <form name="remove_account" id="remove_account" action="/rm_account" method="post">
        <button class="edit" title="Удалить профиль" type="submit" name="id" value="${user.id_user}"> Удалить профиль ❌ </button>
    </form>

</div>

</body>

</html>