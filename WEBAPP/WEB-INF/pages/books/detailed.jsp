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
    <td>Жанр: <td>
    <td> ${genre} </td>
    <br>
    <td>Название: </td>
    <td> ${title} </td>
    <br>
    <td> Авторы: </td>
    <td>
        <c:forEach items = "${book_authors}" var="author" >
            ${author.author.name}
        </c:forEach>
    </td>
    <br>
    <td>Издательство: </td>
    <td> ${publishing_house} </td>
    <br>
    <td>Год издания: </td>
    <td>${publication_year} </td>
    <br>
    <td>Количество страниц:</td>
    <td> ${page_count}</td>
    <br>
    <td> Количество экземпляров: </td>
    <td> ${count_book}</td>
    <br>
    <td>Тип обложки: </td>
    <td> ${cover} </td>
    <br>
    <td>Стоимость: </td>
    <td> ${price} </td>
    <br>
    <form name="edit_book" id="edit_book_form" action="/books/edit" method="post">
        <button id="edit" class="edit" title="Редактировать данные" name="id" value="${id}" type="submit">Редактировать 📝 </button>
    </form>
    <form name="remove_book" id="remove_book_form" action="/books/rm" method="post">
        <button id="remove" class="edit" title="Удалить информацию о книге" name="id" value="${id}" type="submit"> Удалить ❌ </button>
    </form>


</body>

</html>