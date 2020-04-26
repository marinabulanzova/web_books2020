<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Подробная информация о заказе</title>
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

<section>
    <h2>Подробная информация о заказе</h2>
    <h3>Клиент: ${order.customer.surname} ${order.customer.first_name} </h3>
    <h3>Адрес доставки: ${order.delivery_address}</h3>
    <h3>Оплата картой: <c:if test="${order.payment == true}"> да </c:if>
                        <c:if test="${order.payment == false}"> нет </c:if>
    </h3>
    <h3>Дата заказа: ${order.order_date} </h3>
    <h3>Дата доставки: ${order.delivery_date}</h3>
    <h3>Статус: ${order.status}</h3>
    <h3>Полная стоимость: ${full_price} </h3>

    <form name="edit_order" id="edit_order_form" action="/edit_orders" method="post">
        <button class="edit" title="Редактировать данные" name="id" value="${order.id_order}" type="submit">Редактировать 📝 </button>
    </form>
    <form name="remove_order" id="remove_order_form" action="/rm_orders" method="post">
        <button id="remove" class="edit" title="Удалить информацию о заказе" name="id" value="${order.id_order}" type="submit"> Удалить ❌ </button>
    </form>

    <h2> Корзина заказа: </h2>
    <table border="1" bgcolor="#faebd7">
        <tr>
            <th>Название книги</th>
            <th>Стоимость</th>
            <th>Количество экземпляров</th>
        </tr>
        <c:forEach items="${BooksList}" var="book">
            <tr>
                <td>${book.book.title}</td>
                <td>${book.price}</td>
                <td>${book.count_book}</td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>

</html>