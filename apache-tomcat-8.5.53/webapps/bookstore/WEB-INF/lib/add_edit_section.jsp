<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все обязательные поля</p>
    </c:if>
    <form name="book_info_edit" action="/edit_done" method="post">
        <label>
            Жанр
            <input class="medium" type="text" name="genre" <c:if test="${genre != null}"> value="${genre}" </c:if>>
        </label>
        <label>
            Название
            <input class="medium" type="text" name="title" placeholder="Необходимо заполнить это поле" <c:if test="${title != null}"> value="${title}" </c:if>>
        </label>
        <label>
            Авторы:
            <input class="medium" type="text" name="author0" placeholder="Необходимо заполнить это поле" <c:if test="${author0 != null}"> value="${author0}" </c:if>>
            <input class="medium" type="text" name="author1" <c:if test="${author1 != null}"> value="${author1}" </c:if>>
            <input class="short" type="text" name="author2" <c:if test="${author2 != null}"> value="${author2}" </c:if>>
        </label>
        <label>
            Издательство
            <input class="medium" type="text" name="publishing_house" <c:if test="${publishing_house != null}"> value="${publishing_house}" </c:if>>
        </label>
        <label>
            Год издания
            <input class="short" type="number" name="publication_year" <c:if test="${publication_year != null}"> value="${publication_year}" </c:if>>
        </label>
        <label>
            Количество страниц
            <input class="short" type="number" name="page_count" placeholder="Необходимо заполнить это поле" <c:if test="${page_count != null}"> value="${page_count}" </c:if>>
        </label>
        <label>
            Количество экземпляров
            <input class="short" type="number" name="count_book" placeholder="Необходимо заполнить это поле" <c:if test="${count_book != null}"> value="${count_book}" </c:if>>
        </label>
        <label>
            Тип обложки
            <input class="medium" type="text" name="cover" placeholder="Необходимо заполнить это поле" <c:if test="${cover != null}"> value="${cover}" </c:if>>
        </label>
        <label>
            Стоимость
            <input class="short" type="number" name="price" placeholder="Необходимо заполнить это поле" <c:if test="${price != null}"> value="${price}" </c:if>>
        </label>
        <button id="edit" type="submit" <c:if test="${id != null}"> name="id" value="${id}" </c:if>> Готово ✅</button>
    </form>
</section>