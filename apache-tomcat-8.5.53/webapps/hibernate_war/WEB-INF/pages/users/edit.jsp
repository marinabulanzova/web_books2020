--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Изменение информации</title>
</head>

<body>
<%@ include file="../header.jsp" %>
<div id="heading">
    <h1>Изменение информации</h1>
</div>
<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/">Информация о рейсах</a></li>
            <li class="active"><a href="/clients">Информация о клиентах</a></li>
            <li class="submenu"><a href="/clients/add">Добавление клиента</a></li>
            <li class="active_submenu">Изменение информации</li>
            <li><a href="/stations">Информация о станциях</a></li>
            <li><a href="/companies">Информация о компаниях</a></li>
        </ul>
    </nav>
</aside>
<%@ include file="add_edit_section.jsp" %>
</body>

</html>