<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Регистрация</title>
    <meta http-equiv="content-type", content="text/html", charset="utf-8">
</head>

<body>
<%@ include file="header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/">Информация о книгах</a></li>
            <li class="active">Регистрация</li>
            <li class="submenu"><a href="/login">Вход</a></li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Регистрация</h1>
</div>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все обязательные поля</p>
    </c:if>

    <c:if test="${error_number != null}">
        <p class="error">Пользователь с такие номером телефона уже существует</p>
    </c:if>

    <c:if test="${error_email != null}">
        <p class="error">Пользователь с таким e_mail уже существует</p>
    </c:if>

    <form name="client_info_edit" modelAttribute="user" action="/register" method="post">
        <label>
            Фамилия
            <input class="medium" type="text" name="surname" <c:if test="${surname != null}"> value="${surname}" </c:if>>
        </label>
        <br>
        <label>
            Имя
            <input class="medium" type="text" name="first_name" placeholder="Необходимо заполнить это поле" <c:if test="${first_name != null}"> value="${first_name}" </c:if>>
        </label>
        <br>
        <label>
            Отчество
            <input class="medium" type="text" name="patronymic" <c:if test="${patronymic != null}"> value="${patronymic}" </c:if>>
        </label>
        <br>
        <label>
            Адрес
            <input class="long" type="text" name="address" <c:if test="${address != null}"> value="${address}" </c:if>>
        </label>
        <br>
        <label>
            Номер телефона
            <input class="short" type="text" name="phone_number" placeholder="Необходимо заполнить это поле" <c:if test="${phone_number != null}"> value="${phone_number}" </c:if>>
        </label>
        <br>
        <label>
            e-mail
            <input class="medium" type="text" name="e_mail" placeholder="Необходимо заполнить это поле" <c:if test="${e_mail != null}"> value="${e_mail}" </c:if>>
        </label>
        <br>
        <label>
            Пароль
            <input class="medium" type="password" name="password_hash" placeholder="Необходимо заполнить это поле" <c:if test="${password_hash != null}"> value="${password_hash}" </c:if>>
        </label>
        <br>
        <button type="submit"  <c:if test="${id != null}">name="id" value="${id}" </c:if>> Готово ✅</button>
    </form>
</section>
</body>
</html>