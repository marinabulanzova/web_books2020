<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все обязательные поля</p>
    </c:if>
    <form name="order_info_edit" action="/orders/edit_done" method="post">
        <label>
            Дата доставки
            <input class="medium" type="date" name="delivery_date" placeholder="Необходимо заполнить это поле" <c:if test="${delivery_date != null}"> value="${delivery_date}" </c:if>>
        </label>
        <br>
        <label>
            Статус
            <input class="medium" type="text" name="status" placeholder="Необходимо заполнить это поле" <c:if test="${status != null}"> value="${status}" </c:if>>
        </label>
        <br>
        <button type="submit" <c:if test="${id != null}">name="id" value="${id}" </c:if>> Готово ✅</button>
    </form>
</section>