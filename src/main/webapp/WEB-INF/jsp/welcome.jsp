<%--
  Created by IntelliJ IDEA.
  User: artem
  Date: 13.10.2024
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mini UFO Quest</title>
    <style>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>
    <h1 class="welcome-title">Добро пожаловать в игру!</h1>
    <p class="narrative-text">
        Ты очнулся в неизвестном месте, не помня, как здесь оказался. Перед тобой развернется захватывающая история, в которой каждый твой выбор имеет значение. Готов ли ты принять вызов и отправиться в неизвестное?
    </p>

    <form action="game" method="post" class="form-container">
        <label for="playerName">Введите ваше имя:</label>
        <input type="text" id="playerName" name="playerName" required>
        <button type="submit">Начать игру</button>
    </form>
</body>
</html>
