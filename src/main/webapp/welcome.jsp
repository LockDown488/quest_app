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
</head>
<body>
    <h1>Добро пожаловать в квест Mini UFO!</h1>
    <form action="game" method="post">
        <label for="playerName">Введите ваше имя:</label>
        <input type="text" id="playerName" name="playerName" required>
        <button type="submit">Начать игру</button>
    </form>
</body>
</html>
