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


<div id="heading">
    <h2>Подробная информация о заказе</h2>
    <h3>Клиент: ${customer.surname} ${customer.first_name} </h3>
    <h3>Адрес доставки: ${delivery_address}</h3>
    <h3>Оплата картой: ${payment_metod}</h3>
    <h3>Дата заказа: ${order_date} </h3>
    <h3>Дата доставки: ${delivery_date}</h3>
    <h3>Статус: ${status}</h3>
    <h3>Полная стоимость: ${full_price} </h3>

    <form name="edit_order" id="edit_order_form" action="/orders/edit" method="post">
        <button class="edit" title="Редактировать данные" name="id" value="${id}" type="submit">Редактировать 📝 </button>
    </form>
    <form name="remove_order" id="remove_order_form" action="/orders/rm" method="post">
        <button class="edit" title="Удалить информацию о заказе" name="id" value="${id}" type="submit"> Удалить ❌ </button>
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

</div>
</div>

</body>

</html>