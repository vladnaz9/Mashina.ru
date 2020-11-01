<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 01.11.2020
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User" %>
<html>
<head>
    <title>edit</title>
</head>
<body>

<div class="card">
    <%--    тут будет фото--%>
    <img src="" alt="my photo" style="width:100%">


    <h1>${user.getUserName()} <textarea> </textarea> </h1>
    <p class="title">${user.getEmail()}</p>
    <p>${user.getDop_inf()}</p>

    <form method="get" action="${pageContext.request.contextPath}/img" enctype="multipart/form-data">
        <input type="file" name="filename">
        <button type="submit">изменить аватар</button>
    </form>
</div>
</body>
</html>
