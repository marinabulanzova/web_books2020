<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Информация о книгах</title>

</head>

<body>
<%@ include file="header.jsp" %>
<aside>
    <nav>
        <ul class="aside-menu">
            <li class="active">Информация о книгах</li>
            <c:if test="${admin == true}"> <li class="submenu"><a href="/add_books">Добавить новую книгу</a></li> </c:if>
            <c:if test="${admin == true}"> <li><a href="/users">Информация о клиентах</a></li> </c:if>
            <c:if test="${admin == true}"> <li><a href="/orders">Информация о заказах</a></li> </c:if>
            <c:if test="${id == null}"> <li class="submenu"><a href="/register">Регистрация</a></li> </c:if>
            <c:if test="${id == null}"> <li class="submenu"><a href="/login"> Вход</a></li> </c:if>
            <c:if test="${id != null}"> <li> <a href="/account">Профиль</a></li> </c:if>
            <c:if test="${admin == false}"> <li><a href="/my_orders">Мои заказы</a></li> </c:if>
            <c:if test="${admin == false}"> <li><a href="/basket">Корзина</a></li> </c:if>
            <c:if test="${id != null}"> <li class="submenu"><a href="/logout">Выход</a></li> </c:if>
        </ul>
    </nav>
</aside>

<div id="heading">
    <h1>Информация о книгах</h1>
</div>

<section>
    <form name="search_books" action="/search_books" method="get">
        <label>
            Название
            <input class="medium" type="text" name="title" <c:if test="${title != null}"> value="${title}" </c:if>>
            Автор
            <input class="medium" type="text" name="name_author" <c:if test="${name_author != null}"> value="${name_author}" </c:if>>
        </label>
        <label>
            Жанр
            <input class="medium" type="text" name="genre"  <c:if test="${genre != null}"> value="${genre}" </c:if>>
            Издательство
            <input class="medium" type="text" name="publishing_house"  <c:if test="${publishing_house != null}"> value="${publishing_house}" </c:if>>
        </label>
        <label>
            год издания от:
            <input class="short" type="number" name="min_p_year"  <c:if test="${min_p_year != null}"> value="${min_p_year}" </c:if>>
            до:
            <input class="short" type="number" name="max_p_year" <c:if test="${max_p_year != null}"> value="${max_p_year}" </c:if>>
        </label>
        <label>
            количество страниц от:
            <input class="short" type="number" name="min_p_count" <c:if test="${min_p_count != null}"> value="${min_p_count}" </c:if>>
            до:
            <input class="short" type="number" name="max_p_count" <c:if test="${max_p_count != null}"> value="${max_p_count}" </c:if>>
        </label>
        <label>
            количестов экзампляров(не менее)
            <input class="short" type="number" name="count" <c:if test="${count != null}"> value="${count}" </c:if>>
            тип обложки
            <input class="short" type="text" name="cover"  <c:if test="${cover != null}"> value="${cover}" </c:if>>
        </label>
        <label>
            стоимость от:
            <input class="short" type="number" step="0.01" name="min_price" <c:if test="${min_price != null}"> value="${min_price}" </c:if>>
            до:
            <input class="short" type="number" step="0.01" name="max_price" <c:if test="${max_price != null}"> value="${max_price}" </c:if>>
        </label>
        <button id="search" type="submit"> Искать 🔎</button>
    </form>
    <br>
    <c:if test="${BooksList.size() == 0}">
        Книг с такими пареметрами не найдено
    </c:if>
    <c:if test="${BooksList.size() > 0}">
        <table id="tableBooks" border="1" bgcolor="#faebd7">
            <tr>
                <th>Жанр</th>
                <th>Название</th>
                <th>Авторы</th>
                <th>Издательство</th>
                <th>Стоимость</th>
                <th>Подробнее</th>
            </tr>
            <c:forEach items="${BooksList}" var="book">
                <tr>
                    <td>${book.genre}</td>
                    <td>${book.title}</td>
                    <td>
                        <c:forEach items = "${book.book_authors}" var="author" >
                            ${author.author.name}
                            <br>
                        </c:forEach>
                    </td>
                    <td>${book.publishing_house}</td>
                    <td>${book.price}</td>
                    <td>
                        <form name="more_detailed" id="book_more_detailed" action="/detailed_books" method="get">
                            <button class="watch" id="detailed" title="Подробная информация о книге" name="id" value="${book.id_book}" type="submit">  👁 </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</section>
</body>

</html>