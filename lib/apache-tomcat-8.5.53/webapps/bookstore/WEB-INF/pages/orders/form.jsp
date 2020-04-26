<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
    <form name="search_by_private_information" action="/orders/search" method="get">
        <label>
            id клиента
            <input class="medium" type="number" name="customer" placeholder="Любой" <c:if test="${customer != null}"> value="${customer}" </c:if>>
        </label>
        <label>
            Адрес доставки
            <input class="short" type="text" name="delivery_address" placeholder="Любой" <c:if test="${delivery_address != null}"> value="${delivery_address}" </c:if>>
            <br>
        </label>
        <label>
            <input type="checkbox" name="payment_card" title="Оплата картой"  <c:if test="${payment_card != null && payment_card.equals(true)}"> value = "${true}" checked </c:if>
            <c:if test="${payment_card == null}"> value = "${false}" </c:if>>
            Оплата картой
        </label>
        <br>
        <label>
            Статус:
            <input class="short" type="text" name="status" placeholder="Любой" <c:if test="${status != null}"> value="${status}" </c:if>>
        </label>
        <br>
        <label>
            Дата заказа от:
            <input class="medium" type="date" name="min_o_date" placeholder="Любой" <c:if test="${min_o_date != null}"> value="${min_o_date}" </c:if>>
        </label>
        <label>
            до:
            <input class="long" type="date" name="max_o_date" placeholder="Любой" <c:if test="${max_o_date != null}"> value="${max_o_date}" </c:if>>
            <br>
        </label>
        <label>
            Дата доставки от:
            <input class="short" type="date" name="min_d_date" placeholder="Любой" <c:if test="${min_d_date != null}"> value="${min_d_date}" </c:if>>
        </label>
        <label>
            до:
            <input class="medium" type="date" name="max_d_date" placeholder="Любой" <c:if test="${max_d_date != null}"> value="${max_d_date}" </c:if>>
            <br>
        </label>
        <label>
            Стоимость от:
            <input class="short" type="number" step="0.01" name="min_d_price" placeholder="Любой" <c:if test="${min_d_price != null}"> value="${min_d_price}" </c:if>>
        </label>
        <label>
            до:
            <input class="short" type="number" step="0.01" name="max_d_price" placeholder="Любой" <c:if test="${max_d_price != null}"> value="${cover}" </c:if>>
            <br>
        </label>

        <button type="submit"> Искать 🔎</button>
    </form>

    <table border="1" bgcolor="#faebd7">
        <tr>
            <th>Номер заказа</th>
            <th>Адрес доставки</th>
            <th>Дата заказа</th>
            <th>Дата доставки</th>
            <th>Статус </th>
            <th>Подробнее</th>
        </tr>
        <c:forEach items="${OrdersList}" var="order">
            <tr>
                <td>${order.id_order}</td>
                <td>${order.delivery_address}</td>
                <td>${order.order_date}</td>
                <td>${order.delivery_date}</td>
                <td>${order.status}</td>
                <td>
                    <form name="more_detailed" id="order_more_detailed" action="/orders/detailed" method="get">
                        <button class="watch" title="Подробная информация о заказе" name="id" value="${order.id_order}" type="submit">  👁 </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>