<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 27.10.2020
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User" %>

<html>
<head>
    <title>profile</title>
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            max-width: 300px;
            margin: auto;
            text-align: center;
        }

        .title {
            color: grey;
            font-size: 18px;
        }

        button {
            border: none;
            outline: 0;
            display: inline-block;
            padding: 8px;
            color: white;
            background-color: #000;
            text-align: center;
            cursor: pointer;
            width: 100%;
            font-size: 18px;
        }

        a {
            text-decoration: none;
            font-size: 22px;
            color: black;
        }

        button:hover, a:hover {
            opacity: 0.7;
        }
    </style>
</head>
<body>

<div class="card">
    <%--    тут будет фото--%>
    <img src="" alt="my photo" style="width:100%">
    <h1>${user.getUserName()} </h1>


    <p class="title">${user.getEmail()}</p>
    <p>${user.getDop_inf()}</p>
    <%--    <p><button>Contact</button></p>--%>
    <form method="post" action="${pageContext.request.contextPath}/img" enctype="multipart/form-data">
        <input type="file" name="filename">
        <button type="submit">изменить аватар</button>
    </form>
</div>
<form method="get" action="${pageContext.request.contextPath}/logout" enctype="multipart/form-data">
    <ul>
        <li>
            <button type="submit"> выход </button>
        </li>
    </ul>
</form>
</body>
</html>
