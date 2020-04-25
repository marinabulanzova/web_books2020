<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Подробная информация о книге</title>
</head>

<body>
<%@ include file="../header.jsp" %>

    <h2>Подробная информация о книге</h2>
    <br>
    <td>Жанр: <td>
    <td> ${book.genre} </td>
    <br>
    <td>Название: </td>
    <td> ${book.title} </td>
    <br>
    <td> Авторы: </td>
    <td>
        <c:forEach items = "${book.book_authors}" var="author" >
            ${author.author.name}
        </c:forEach>
    </td>
    <br>
    <td>Издательство: </td>
    <td> ${book.publishing_house} </td>
    <br>
    <td>Год издания: </td>
    <td>${book.publication_year} </td>
    <br>
    <td>Количество страниц:</td>
    <td> ${book.page_count}</td>
    <br>
    <td> Количество экземпляров: </td>
    <td> ${book.count_book}</td>
    <br>
    <td>Тип обложки: </td>
    <td> ${book.cover} </td>
    <br>
    <td>Стоимость: </td>
    <td> ${book.price} </td>
    <br>
    <c:if test="${admin == true}" >
        <form name="edit_book" id="edit_book_form" action="/edit_books" method="post">
            <button id="edit" class="edit" title="Редактировать данные" name="id" value="${book.id_book}" type="submit">Редактировать 📝 </button>
        </form>

        <form name="remove_book" id="remove_book_form" action="/rm_books" method="post">
            <button id="remove" class="edit" title="Удалить информацию о книге" name="id" value="${book.id_book}" type="submit"> Удалить ❌ </button>
        </form>
    </c:if>


    <c:if test="${admin == false}" >
        <br>
        <form name="add_basket" action="/add_basket" method="post">
            <label>
                Необходимое количество экземпляров
                <input class="medium" type="number" name="count" placeholder="не больше количества экземпляров книги" >
            </label>
            <button type="submit" name="id" value="${book.id_book}"> Добавить в корзину </button>
        </form>
    </c:if>

</body>

</html>