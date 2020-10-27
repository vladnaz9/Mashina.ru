<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 26.10.2020
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<div class="welcome">
    <span class="text">WELCOME to our site</span>
</div>

<div class="login">
    <header class="login-header"><span class="text">LOGIN</span> <span class="loader>"></span></header>
    <form action="${pageContext.request.contextPath}/login" method="post" autocomplete="off">
        <div class="form-group">
            <span class="text-black-50">Почта</span>
            <input type="email" class="form-control" name="email">
        </div>
        <div class="form-group">
            <span class="text-black-50">Пароль</span>
            <input type="password" class="form-control" name="password"
            >
        </div>
        <div class="form-group">
            <input type="checkbox" name="remember" class="form-check-input"
                   id="exampleCheck1">
            <label class="form-check-label" for="exampleCheck1">Запомнить меня?</label>
        </div>
        <button type="submit"> Войти </button>
    </form>
</div>
</body>
</html>
