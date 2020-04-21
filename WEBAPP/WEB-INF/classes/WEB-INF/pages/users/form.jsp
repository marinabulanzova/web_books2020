<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section>
        <form name="search_by_private_information" action="/users/search" method="get">
            <label>
                Фамилия
                <input class="medium" type="text" name="surname" placeholder="Любая" <c:if test="${surname != null}"> value="${surname}" </c:if>>
            </label>
            <label>
                Имя
                <input class="medium" type="text" name="first_name" placeholder="Любое" <c:if test="${first_name != null}"> value="${first_name}" </c:if>>
            </label>
            <label>
                Отчество
                <input class="medium" type="text" name="patronymic" placeholder="Любое" <c:if test="${patronymic != null}"> value="${patronymic}" </c:if>>
                <br>
            </label>
            <label>
                Адрес
                <input class="long" type="text" name="address" placeholder="Любой" <c:if test="${address != null}"> value="${address}" </c:if>>
                <br>
            </label>
            <label>
                Телефон
                <input class="short" type="text" name="phone_number" placeholder="Любой" <c:if test="${phone_number != null}"> value="${phone_number}" </c:if>>
            </label>
            <label>
                e-mail
                <input class="medium" type="email" name="e_mail" placeholder="Любой" <c:if test="${e_mail != null}"> value="${e_mail}" </c:if>>
                <br>
            </label>
            <button type="submit"> Искать 🔎</button>
        </form>
    <table border="1" bgcolor="#d8bfd8">
        <tr>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Телефон</th>
            <th>e-mail</th>
            <th>Подробнее</th>
        </tr>
        <c:forEach items="${UsersList}" var="user">
            <tr>
                <td>${user.surname}</td>
                <td>${user.first_name}</td>
                <td>${user.phone_number}</td>
                <td>${user.e_mail}</td>
                <td>
                    <form name="more_detailed" id="user_more_detailed" action="/users/detailed" method="get">
                        <button class="watch" title="Смотреть" name="id" value="${user.id_user}" type="submit"> 👁 </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>