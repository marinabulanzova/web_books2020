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


<div id="heading">
    <h2>Подробная информация о книге</h2>
    <h3>Жанр: ${genre} </h3>
    <h3>Название: ${title} </h3>
    <h3> Авторы:
        <c:forEach items = "${book_authors}" var="author" >
            ${author.author.name}
        </c:forEach>
    </h3>
    <h3>Издательство: ${publishing_house} </h3>
    <h3>Год издания: ${publication_year} </h3>
    <h3>Количество страниц: ${page_count}</h3>
    <h3>Количество экземпляров: ${count_book}</h3>
    <h3>Тип обложки: ${cover} </h3>
    <h3>Стоимость: ${price} </h3>
    <form name="edit_book" id="edit_book_form" action="/books/edit" method="post">
        <button class="edit" title="Редактировать данные" name="id" value="${id}" type="submit">Редактировать 📝 </button>
    </form>
    <form name="remove_book" id="remove_book_form" action="/books/rm" method="post">
        <button class="edit" title="Удалить информацию о книге" name="id" value="${id}" type="submit"> Удалить ❌ </button>
    </form>

</div>
</div>

</body>

</html>