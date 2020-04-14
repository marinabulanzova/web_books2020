<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Подробная информация о клиенте</title>
</head>

<body>
<%@ include file="../header.jsp" %>


<div id="heading">
    <h2>Подробная информация о клиенте</h2>
    <h3> Фамилия: ${surname} </h3>
    <h3>Имя: ${first_name} </h3>
    <h3>Отчество: ${patronymic} </h3>
    <h3>Адрес: ${address} </h3>
    <h3>e_mail: ${e_mail}</h3>
    <h3>Номер телефона: ${phone_number} </h3>
    <form name="edit_user" id="edit_user_form" action="/users/edit" method="post">
        <button class="edit" title="Редактировать данные" name="id" value="${id}" type="submit">Редактировать 📝 </button>
    </form>
    <form name="remove_user" id="remove_user_form" action="/users/rm" method="post">
        <button class="edit" title="Удалить информацию о клиенте" name="id" value="${id}" type="submit"> Удалить ❌ </button>
    </form>
    <h2> Заказы </h2>
    <table border="1" bgcolor="#d8bfd8">
        <tr>
            <th>Номер заказа</th>
            <th>Дата заказа</th>
            <th>Дата доставки</th>
            <th>Статус</th>
            <th>Подробнее</th>
        </tr>
        <c:forEach items="${OrdersList}" var="order">
            <tr>
                <td>${order.id_order}</td>
                <td>${order.order_date}</td>
                <td>${order.delivery_date}</td>
                <td>${order.status}</td>
                <td>
                    <form name="more_detailed" id="order_more_detailed" action="/orders/detailed" method="get">
                        <button class="watch" title="Смотреть" name="id" value="${order.id_order}" type="submit"> 👁 </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</div>

</body>

</html>