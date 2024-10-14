<%--
  Created by IntelliJ IDEA.
  User: artem
  Date: 13.10.2024
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mini UFO Quest</title>
    <style>
        .secret-button {
            background-color: #f0f0f0;
            color: #f0f0f0;
            border: none;
        }
        .secret-button:hover {
            color: #ff0000;
            background-color: #fff0f0;
        }
    </style>
</head>
<body>
    <h1>Привет, ${sessionScope.gameSession.playerName}!</h1>
    <p>${sessionScope.gameSession.currentChoice}</p>

    <form action="game" method="post">
        <c:choose>
            <c:when test="${sessionScope.gameSession.gameOver}">
                <h2>Конец игры</h2>
                <p>${sessionScope.gameSession.gameResult}</p>
                <p>Количество сыгранных игр: ${sessionScope.gameSession.gamesPlayed}</p>
                <button type="submit" name="restart">Начать заново</button>
            </c:when>
            <c:otherwise>
                <c:forEach items="${sessionScope.gameSession.availableChoices}" var="choice">
                    <c:choose>
                        <c:when test="${choice == 'secret'}">
                            <button type="submit" name="choice" value="${choice}" class="secret-button">Очнись!</button>
                        </c:when>
                        <c:otherwise>
                            <button type="submit" name="choice" value="${choice}">${choice}</button>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </form>

    <hr>
    <h3>Статистика пользователя</h3>
    <p>Имя игрока: ${sessionScope.gameSession.playerName}</p>
    <p>Количество игр: ${sessionScope.gameSession.gamesPlayed}</p>
    <p>IP-адрес: ${userIp}</p>
    <p>Длина имени игрока: ${nameLength}</p>
</body>
</html>
