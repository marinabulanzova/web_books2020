<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Информация о заказах</title>
</head>

<body>
<%@ include file="header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li><a href="/" >Информация о книгах</a></li>
            <li class="submenu"><a href="/add_books">Добавить новую книгу</a></li>
            <li><a href="/users">Информация о клиентах </a></li>
            <li class="active"> Информация о заказах</li>
            <li class="submenu"><a href="/logout"> Выход </a></li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Информация о заказах</h1>
</div>

<section>
    <form name="search_orders" action="/search_orders" method="get">
        <label>
            Адрес доставки
            <input class="short" type="text" name="delivery_address" <c:if test="${delivery_address != null}"> value="${delivery_address}" </c:if>>
        </label>
        <label>
            <input type="checkbox" name="payment_card" title="Оплата картой"  <c:if test="${payment_card != null && payment_card.equals(true)}"> value = "${true}" checked </c:if>
            <c:if test="${payment_card == null}"> value = "${false}" </c:if>>
            Оплата картой
        </label>
        <label>
            Статус:
            <input class="short" type="text" name="status" <c:if test="${status != null}"> value="${status}" </c:if>>
        </label>
        <label>
            Дата заказа от:
            <input class="medium" type="date" name="min_o_date" <c:if test="${min_o_date != null}"> value="${min_o_date}" </c:if>>
        </label>
        <label>
            до:
            <input class="long" type="date" name="max_o_date" <c:if test="${max_o_date != null}"> value="${max_o_date}" </c:if>>
            <br>
        </label>
        <label>
            Дата доставки от:
            <input class="short" type="date" name="min_d_date" <c:if test="${min_d_date != null}"> value="${min_d_date}" </c:if>>
        </label>
        <label>
            до:
            <input class="medium" type="date" name="max_d_date" <c:if test="${max_d_date != null}"> value="${max_d_date}" </c:if>>
        </label>
        <label>
            Стоимость доставки от:
            <input class="short" type="number" step="0.01" name="min_d_price" <c:if test="${min_d_price != null}"> value="${min_d_price}" </c:if>>
        </label>
        <label>
            до:
            <input class="short" type="number" step="0.01" name="max_d_price" <c:if test="${max_d_price != null}"> value="${max_d_price}" </c:if>>
        </label>

        <button type="submit"> Искать 🔎</button>
    </form>
    <br>
    <table border="1" bgcolor="#faebd7">
        <tr>
            <th>Номер заказа</th>
            <th>Адрес доставки</th>
            <th>Дата заказа</th>
            <th>Дата доставки</th>
            <th>Статус </th>
            <th>Подробнее</th>
        </tr>
        <c:forEach items="${OrdersList}" var="order">
            <tr>
                <td>${order.id_order}</td>
                <td>${order.delivery_address}</td>
                <td>${order.order_date}</td>
                <td>${order.delivery_date}</td>
                <td>${order.status}</td>
                <td>
                    <form name="more_detailed" id="order_more_detailed" action="/detailed_orders" method="get">
                        <button class="watch" title="Подробная информация о заказе" name="id" value="${order.id_order}" type="submit">  👁 </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>

</html>