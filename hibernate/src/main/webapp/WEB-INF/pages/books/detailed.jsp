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

<aside>
    <nav>
        <ul class="aside-menu">
            <li> <a href="/">Информация о книгах</a></li>
            <c:if test="${admin == true}"> <li class="submenu"><a href="/add_books">Добавить новую книгу</a></li> </c:if>
            <c:if test="${admin == true}"> <li><a href="/users">Информация о клиентах</a></li> </c:if>
            <c:if test="${admin == true}"> <li><a href="/orders">Информация о заказах</a></li> </c:if>
            <c:if test="${admin == null}"> <li class="submenu"><a href="/register">Регистрация</a></li> </c:if>
            <c:if test="${admin == null}"> <li class="submenu"><a href="/login"> Вход</a></li> </c:if>
            <c:if test="${admin != null}"> <li> <a href="/account">Профиль</a></li> </c:if>
            <c:if test="${admin == false}"> <li><a href="/my_orders">Мои заказы</a></li> </c:if>
            <c:if test="${admin == false}"> <li><a href="/basket">Корзина</a></li> </c:if>
            <c:if test="${admin != null}"> <li class="submenu"><a href="/logout">Выход</a></li> </c:if>
        </ul>
    </nav>
</aside>
<section>
    <h2>Подробная информация о книге</h2>
    <h3>Жанр: ${book.genre} </h3>
    <h3>Название: ${book.title} </h3>
    <h3>Авторы:<c:forEach items = "${book.book_authors}" var="author" >
        ${author.author.name}
        </c:forEach>
    </h3>
    <h3>Издательство: ${book.publishing_house}</h3>
    <h3>Год издания: ${book.publication_year}  </h3>
    <h3>Количество страниц: ${book.page_count} </h3>
    <h3>Количество экземпляров: ${book.count_book}</h3>
    <h3>Тип обложки: ${book.cover} </h3>
    <h3>Стоимость: ${book.price} </h3>

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
            <button class="add" type="submit" name="id" value="${book.id_book}"> Добавить в корзину </button>
        </form>
    </c:if>
</section>

</body>

</html>