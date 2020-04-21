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
<div id="heading">
    <h1>Вход</h1>
</div>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все поля</p>
    </c:if>

    <c:if test="${error_password != null}">
        <p class="error">Проверьте правильность введённого пароля</p>
    </c:if>

    <c:if test="${error_email != null}">
        <p class="error">Пользователь с таким e_mail не существует</p>
    </c:if>

    <form name="user_info_edit" action = "${pageContext.request.contextPath}/j_spring_security_check" method="post">
        <label>
            e-mail
            <input class="medium" type="text" name="e_mail" placeholder="Необходимо заполнить это поле" <c:if test="${e_mail != null}"> value="${e_mail}" </c:if>>
        </label>
        <br>
        <label>
            Пароль
            <input class="medium" type="password" name="password" placeholder="Необходимо заполнить это поле" <c:if test="${password != null}"> value="${password}" </c:if>>
        </label>
        <br>
        <button type="submit" > Войти ✅</button>
    </form>
</section>

</body>
</html>