<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Заказы</title>
</head>

<body>
<%@ include file="../header.jsp" %>

<aside>
    <nav>
        <ul class="aside-menu">
            <li> <a href ="/"> Информация о книгах </a></li>
            <li><a href="/account">Профиль </a></li>
            <li class="active ">Мои заказы</li>
            <li><a href="/basket">Корзина</a></li>
            <li class="submenu"><a href="/logout">Выход</a></li>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Мои заказы</h1>
</div>
<div >
    <c:if test="${OrdersList.size() == 0}">
         У вас пока нет заказов
    </c:if>
    <c:if test="${OrdersList.size() > 0}">
        <table border="1" bgcolor="#faebd7">
            <tr>
                <th>номер заказа</th>
                <th>адрес заказа</th>
                <th>время заказа</th>
                <th>дата доставки</th>
                <th>статус</th>
                <th>книги в заказе</th>
                <th>стоимость доставки</th>
            </tr>
            <c:forEach items="${OrdersList}" var="order">
                <tr>
                    <td>${order.id_order}</td>
                    <td>${order.delivery_address}</td>
                    <td>${order.order_date}</td>
                    <td>${order.delivery_date}</td>
                    <td>${order.status}</td>
                    <td> <c:forEach items="${order.basket_orderList}" var="b_o" >
                        Название: ${b_o.book.title} <br>
                        Цена: ${b_o.price} <br>
                        Количество: ${b_o.count_book}
                        <br>
                    </c:forEach>
                    <td>${order.delivery_price}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>

</body>

</html>