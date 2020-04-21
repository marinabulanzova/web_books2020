<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/Oswald.css" />">
    <title>Вход</title>
    <meta http-equiv="content-type", content="text/html", charset="utf-8">
</head>

<body>
<%@ include file="header.jsp" %>
<div id="heading">
    <h1>Вход</h1>
</div>

<form method = "POST"
      action = "${pageContext.request.contextPath}/j_spring_security_check">
    <table align = "center">
        <tr>
            <td>Электронная почта</td>
            <td>
                <label>
                    <input name = "e_mail" type = "email"/>
                </label>
            </td>
        </tr>

        <tr>
            <td>Пароль</td>
            <td>
                <label>
                    <input type = "password" name = "password"/>
                </label>
            </td>
        </tr>

        <tr>
            <td>&nbsp;</td>
            <td>
                <label>
                    <input type = "submit" value = "Вход"/>
                </label>
            </td>
        </tr>
    </table>
</form>

</body>
</html>