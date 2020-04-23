<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <c:if test="${error != null}">
        <p class="error">Пожалуйста, заполните все обязательные поля</p>
    </c:if>
    <form name="order_info_edit" action="/edit_done_orders" method="post">
        <label>
            Дата доставки
            <input class="medium" type="date" name="delivery_date" placeholder="Необходимо заполнить это поле" <c:if test="${delivery_date != null}"> value="${delivery_date}" </c:if>>
        </label>
        <br>
        <label>
            Статус
            <input class="medium" type="text" name="status" placeholder="Необходимо заполнить это поле" <c:if test="${status != null}"> value="${status}" </c:if>>
        </label>

        <form name="edit_order" id="edit_order_form" action="/detailed_orders" method="post">
            <button class="edit" title="отмена" name="id" value="${id}" type="submit">Отмена  </button>
        </form>
        <button type="submit" <c:if test="${id != null}">name="id" value="${id}" </c:if>> Редактировать ✅</button>

    </form>
</section>