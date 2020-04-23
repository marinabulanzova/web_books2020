<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Корзина</title>
</head>

<body>
<%@ include file="../header.jsp" %>

<div id="heading">
    <c:if test="${BooksList.size() == 0}">
        В корзине нет ни одной книги
    </c:if>
    <c:if test="${BooksList.size() > 0}">
        <h2> Книги в корзине: </h2>
        <table border="1" bgcolor="#faebd7">
            <tr>
                <th>Название книги</th>
                <th>Стоимость</th>
                <th>Количество экземпляров</th>
                <th>Удалить</th>
            </tr>
            <c:forEach items="${BooksList}" var="book">
                <tr>
                    <td>${book.book.title}</td>
                    <td>${book.book.price}</td>
                    <td>${book.count_book}</td>
                    <td> <form name="rm_book_basket" id="rm_book_basket" action="/rm_book_basket" method="post">
                        <button class="edit" title="Удалить книгу из корзины" name="id" value="${book.id}" type="submit"> ❌ </button>
                    </form></td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <form name="basket" action = "/add_orders" method="post">
            <label>
                Адрес доставки
                <input class="medium" type="text" name="delivery_address" placeholder="Необходимо заполнить это поле" >
            </label>
            <br>
            <label>
                Оплата картой
                <input type="checkbox" name="payment_card" title="" <c:if test="${payment_card != null && payment_card.equals(true)}"> checked </c:if>>
            </label>
            <br>
            <button type="submit" > Оформить заказ ✅</button>
        </form>
    </c:if>

</div>
</div>

</body>

</html>