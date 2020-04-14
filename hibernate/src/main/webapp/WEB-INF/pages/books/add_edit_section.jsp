<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все обязательные поля</p>
    </c:if>
    <form name="book_info_edit" action="/books/edit_done" method="post">
        <label>
            Жанр
            <input class="medium" type="text" name="genre" <c:if test="${ganre != null}"> value="${ganre}" </c:if>>
        </label>
        <br>
        <label>
            Название
            <input class="medium" type="text" name="title" placeholder="Необходимо заполнить это поле" <c:if test="${title != null}"> value="${title}" </c:if>>
        </label>
        <br>
        <label>
            Авторы:
            <input class="medium" type="text" name="authors" placeholder="Необходимо заполнить это поле" <c:if test="${authors.get(0) != null}"> value="${authors.get(0)}" </c:if>>
            <input class="medium" type="text" name="authors" <c:if test="${authors.get(1) != null}"> value="${authors.get(1)}" </c:if>>
            <input class="medium" type="text" name="authors" <c:if test="${authors.get(2) != null}"> value="${authors.get(2)}" </c:if>>
        </label>
        <br>
        <label>
            Издательство
            <input class="medium" type="text" name="publishing_house" <c:if test="${publishing_house != null}"> value="${publishing_house}" </c:if>>
        </label>
        <br>
        <label>
            Год издания
            <input class="long" type="number" name="publication_year" <c:if test="${publication_year != null}"> value="${publication_year}" </c:if>>
        </label>
        <br>
        <label>
            Количество страниц
            <input class="short" type="number" name="page_count" placeholder="Необходимо заполнить это поле" <c:if test="${page_count != null}"> value="${page_count}" </c:if>>
        </label>
        <br>
        <label>
            Количество экземпляров
            <input class="medium" type="number" name="count_book" placeholder="Необходимо заполнить это поле" <c:if test="${count_book != null}"> value="${count_book}" </c:if>>
        </label>
        <br>
        <label>
            Тип обложки
            <input class="medium" type="text" name="cover" placeholder="Необходимо заполнить это поле" <c:if test="${cover != null}"> value="${cover}" </c:if>>
        </label>
        <label>
            Стоимость
            <input class="medium" type="number" name="price" placeholder="Необходимо заполнить это поле" <c:if test="${price != null}"> value="${price}" </c:if>>
        </label>
        <br>
        <button type="submit" <c:if test="${id != null}">name="id" value="${id}" </c:if>> Готово ✅</button>
    </form>
</section>