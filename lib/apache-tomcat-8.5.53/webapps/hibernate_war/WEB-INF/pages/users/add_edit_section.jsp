<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все обязательные поля</p>
    </c:if>
    <form name="client_info_edit" action="/users/edit_done" method="post">
        <label>
            Фамилия
            <input class="medium" type="text" name="surname" <c:if test="${surname != null}"> value="${surname}" </c:if>>
        </label>
        <label>
            Имя
            <input class="medium" type="text" name="first_name" placeholder="Необходимо заполнить это поле" <c:if test="${first_name != null}"> value="${first_name}" </c:if>>
        </label>
        <label>
            Отчество
            <input class="medium" type="text" name="patronymic" <c:if test="${patronymic != null}"> value="${patronymic}" </c:if>>
        </label>
        <label>
            Адрес
            <input class="long" type="text" name="address" <c:if test="${address != null}"> value="${address}" </c:if>>
        </label>
        <label>
            Номер телефона
            <input class="short" type="text" name="phone_number" placeholder="Необходимо заполнить это поле" <c:if test="${phone_number != null}"> value="${phone_number}" </c:if>>
        </label>
        <label>
            e-mail
            <input class="medium" type="text" name="e_mail" placeholder="Необходимо заполнить это поле" <c:if test="${e_mail != null}"> value="${e_mail}" </c:if>>
        </label>
        <br>
        <button type="submit" <c:if test="${id != null}">name="id" value="${id}" </c:if>> Готово ✅</button>
    </form>
</section>