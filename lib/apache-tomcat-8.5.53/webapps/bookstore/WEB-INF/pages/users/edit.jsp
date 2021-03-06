<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title> Редактирование профиля </title>
</head>

<body>
<%@ include file="../header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/account">Профиль </a></li>
            <li><a href="/my_orders">Мои заказы</a></li>
            <li><a href="/basket">Корзина </a></li>
            <li class="submenu"><a href="/logout">Выход</a></li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Редактирование профиля</h1>
</div>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все обязательные поля</p>
    </c:if>

    <c:if test="${error_number != null}">
        <p class="error">Пользователь с таким номером телефона уже существует</p>
    </c:if>

    <c:if test="${error_email != null}">
        <p class="error">Пользователь с таким e_mail уже существует</p>
    </c:if>

    <c:if test="${error_old_password != null}" >
        <p class="error"> Неверно указан пароль </p>
    </c:if>

    <c:if test="${error_new_password != null}" >
        <p class="error"> Не заполнено поле с новым паролем </p>
    </c:if>

    <form name="client_info_edit" modelAttribute="userForm" action="/edit_account" method="post">
        <label>
            Фамилия
            <input class="medium" type="text" name="surname" <c:if test="${userForm.surname != null}"> value="${userForm.surname}" </c:if>>
        </label>
        <label>
            Имя
            <input class="medium" type="text" name="first_name" placeholder="Необходимо заполнить это поле" <c:if test="${userForm.first_name != null}"> value="${userForm.first_name}" </c:if>>
        </label>
        <label>
            Отчество
            <input class="medium" type="text" name="patronymic" <c:if test="${userForm.patronymic != null}"> value="${userForm.patronymic}" </c:if>>
        </label>
        <label>
            Адрес
            <input class="long" type="text" name="address" <c:if test="${userForm.address != null}"> value="${userForm.address}" </c:if>>
        </label>
        <label>
            Номер телефона
            <input class="medium" type="text" name="phone_number" placeholder="Необходимо заполнить это поле" <c:if test="${userForm.phone_number != null}"> value="${userForm.phone_number}" </c:if>>
        </label>
        <label>
            e-mail
            <input class="medium" type="text" name="e_mail" placeholder="Необходимо заполнить это поле" <c:if test="${userForm.e_mail != null}"> value="${userForm.e_mail}" </c:if>>
        </label>
        Заполнять следующие поля только в случае изменения пароля
        <br>
        <label>
            Старый пароль
            <input class="medium" type="password" name="old_password" placeholder="Необходимо заполнить это поле">
        </label>
        <label>
            Новый пароль
            <input class="medium" type="password" name="new_password" placeholder="Необходимо заполнить это поле">
        </label>
        <button class="edit" type="submit" name="id" value="${userForm.id_user}"> Редактировать ✅</button>
    </form>
</section>

</body>

</html>