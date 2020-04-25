<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Подробная информация о клиенте</title>
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
    <h2>Подробная информация о клиенте</h2>
    <h3> Фамилия: ${user.surname} </h3>
    <h3>Имя: ${user.first_name} </h3>
    <h3>Отчество: ${user.patronymic} </h3>
    <h3>Адрес: ${user.address} </h3>
    <h3>e_mail: ${user.e_mail}</h3>
    <h3>Номер телефона: ${user.phone_number} </h3>
    <form name="remove_user" id="remove_user_form" action="/rm_users" method="post">
        <button class="edit" title="Удалить информацию о клиенте" name="id" value="${user.id_user}" type="submit"> Удалить ❌ </button>
    </form>
    <br>

    <h2> Заказы </h2>
    <c:if test="${user.orders.size() == 0}">
        У данного клиента пока нет заказов
    </c:if>
    <c:if test="${user.orders.size() != 0}">
        <table border="1" bgcolor="#d8bfd8">
            <tr>
                <th>Номер заказа</th>
                <th>Дата заказа</th>
                <th>Дата доставки</th>
                <th>Статус</th>
                <th>Подробнее</th>
            </tr>
            <c:forEach items="${user.orders}" var="order">
                <tr>
                    <td>${order.id_order}</td>
                    <td>${order.order_date}</td>
                    <td>${order.delivery_date}</td>
                    <td>${order.status}</td>
                    <td>
                        <form name="more_detailed" id="order_more_detailed" action="/detailed_orders" method="get">
                            <button class="watch" title="Смотреть" name="id" value="${order.id_order}" type="submit"> 👁 </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>

</body>

</html>