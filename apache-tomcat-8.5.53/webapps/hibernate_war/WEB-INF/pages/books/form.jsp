<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <form name="search_by_private_information" action="/books/search" method="post">
        <label>
            Название
            <input class="medium" type="text" name="title" placeholder="Любой" <c:if test="${title != null}"> value="${title}" </c:if>>
        </label>
        <label>
            Автор
            <input class="short" type="text" name="name_author" placeholder="Любой" <c:if test="${name_author != null}"> value="${name_author}" </c:if>>
            <br>
        </label>
        <label>
            Жанр
            <input class="medium" type="text" name="genre" placeholder="Любой" <c:if test="${genre != null}"> value="${genre}" </c:if>>
        </label>
        <label>
            Издательство
            <input class="medium" type="text" name="publishing_house" placeholder="Любой" <c:if test="${publishing_house != null}"> value="${publishing_house}" </c:if>>
            <br>
        </label>
        <label>
            год издания от:
            <input class="long" type="number" name="min_p_year" placeholder="Любой" <c:if test="${min_p_year != null}"> value="${min_p_year}" </c:if>>
        </label>
        <label>
            до:
            <input class="short" type="number" name="max_p_year" placeholder="Любой" <c:if test="${max_p_year != null}"> value="${max_p_year}" </c:if>>
            <br>
        </label>
        <label>
            количество страниц от:
            <input class="medium" type="number" name="min_p_count" placeholder="Любой" <c:if test="${min_p_count != null}"> value="${min_p_count}" </c:if>>
        </label>
        <label>
            до:
            <input class="short" type="number" name="max_p_count" placeholder="Любой" <c:if test="${max_p_count != null}"> value="${max_p_count}" </c:if>>
            <br>
        </label>
        <label>
            количестов экзампляров(не менее)
            <input class="short" type="number" name="count" placeholder="Любой" <c:if test="${count != null}"> value="${count}" </c:if>>
        </label>
        <label>
            тип обложки
            <input class="short" type="text" name="cover" placeholder="Любой" <c:if test="${cover != null}"> value="${cover}" </c:if>>
            <br>
        </label>
        <label>
            стоимость от:
            <input class="short" type="number" name="min_price" placeholder="Любой" <c:if test="${min_price != null}"> value="${min_price}" </c:if>>
        </label>
        <label>
            до:
            <input class="short" type="number" name="max_price" placeholder="Любой" <c:if test="${max_price != null}"> value="${max_price}" </c:if>>
            <br>
        </label>
        <button type="submit"> Искать 🔎</button>
    </form>

    <table border="1" bgcolor="#faebd7">
        <tr>
            <th>Жанр</th>
            <th>Название</th>
            <th>Авторы</th>
            <th>издательство</th>
            <th>стоимость</th>
            <th>Подробнее</th>
        </tr>
        <c:forEach items="${BooksList}" var="book">
            <tr>
                <td>${book.genre}</td>
                <td>${book.title}</td>
                <td>${book.cover}</td>
                <td>${book.publishing_house}</td>
                <td>${book.price}</td>
                <td>
                    <form name="more_detailed" id="book_more_detailed" action="/books/detailed" method="get">
                        <button class="edit" title="Подробная информация о книге" name="book" value="${book.id_book}" type="submit">  👁 </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>