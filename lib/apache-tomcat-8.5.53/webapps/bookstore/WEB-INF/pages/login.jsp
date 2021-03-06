<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Вход</title>
    <meta http-equiv="content-type", content="text/html", charset="utf-8">
</head>

<body>
<%@ include file="header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/">Информация о книгах</a></li>
            <li class="submenu"><a href="/register">Регистрация</a></li>
            <li class="active">Вход</li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Вход</h1>
</div>

<section>
    <c:if test="${error != null}">
        <p class="error"> неверно указан логин или пароль</p>
    </c:if>

    <form name="user_info_edit" action = "${pageContext.request.contextPath}/j_spring_security_check" method="post">
        <label>
            e-mail
            <input class="medium" type="text" name="e_mail" placeholder="Необходимо заполнить это поле" <c:if test="${e_mail != null}"> value="${e_mail}" </c:if>>
        </label>
        <label>
            Пароль
            <input class="medium" type="password" name="password" placeholder="Необходимо заполнить это поле" <c:if test="${password != null}"> value="${password}" </c:if>>
        </label>
        <button id="login" type="submit" > Войти ✅</button>
    </form>
</section>

</body>
</html>