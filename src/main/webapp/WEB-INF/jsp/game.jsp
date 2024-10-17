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
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
</head>
<body>
    <div class="content">
        <h1>Привет, ${sessionScope.gameSession.playerName}!</h1>
        <p class="narrative-text">${sessionScope.gameSession.currentChoice}</p>

        <form action="game" method="post">
            <c:choose>
                <c:when test="${sessionScope.gameSession.gameOver}">
                    <h2>Конец игры</h2>
                    <p class="narrative-text">${sessionScope.gameSession.gameResult}</p>
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
    </div>

    <hr>
    <div class="user-statistics">
        <h3>Статистика пользователя</h3>
        <p>Имя игрока: <span>${sessionScope.gameSession.playerName}</span></p>
        <p>Количество игр: <span>${sessionScope.gameSession.gamesPlayed}</span></p>
        <p>IP-адрес: <span>${userIp}</span></p>
        <p>Длина имени игрока: <span>${nameLength}</span></p>
        <p>Секретная концовка:
            <span class="secret-ending-status ${sessionScope.gameSession.secretEndingFound ? 'found' : 'not-found'}">
            <c:choose>
                <c:when test="${sessionScope.gameSession.secretEndingFound}">
                    Найдена
                </c:when>
                <c:otherwise>
                    Не найдена
                </c:otherwise>
            </c:choose>
        </span>
        </p>
    </div>
</body>
</html>
