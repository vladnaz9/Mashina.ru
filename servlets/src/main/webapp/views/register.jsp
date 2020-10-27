<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 26.10.2020
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post" autocomplete="off">
    <div class="form-group">
        <span class="text-black-50">Почта</span>
        <input type="email" data-verify="email" class="form-control" name="email"
               required>
    </div>
    <div class="form-group">
        <span class="text-black-50">Имя</span>
        <input type="text" class="form-control" name="userName" required>
    </div>

    <div class="form-group">
        <span class="text-black-50">Пароль</span>
        <input type="password" class="form-control" name="password"
               required>
    </div>
    <div class="form-group">
        <span class="text-black-50">Повторить пароль</span>
        <input type="password" class="form-control" name="password_again"
               required>
    </div>

    <div class="form-group">
        <span class="text-black-50">Доп инф</span>
        <input type="text" class="form-control" name="dop_inf"
               required>
    </div>
    <button class="btn btn-primary btn-lg btn-block waves-effect waves-light"
            type="submit">Войти в аккаунт
    </button>
</form>
</body>
</html>
