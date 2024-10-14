package ru.kopanev.spring.service;

import ru.kopanev.spring.model.GameSession;

import javax.servlet.http.HttpSession;

public class GameService {
    public static GameSession initializeGame(HttpSession session, String playerName) {
        GameSession gameSession = new GameSession(playerName);
        session.setAttribute("gameSession", gameSession);
        return gameSession;
    }

    public static void makeChoice(GameSession gameSession, String choice) {
        gameSession.makeChoice(choice);
    }

    public static void restartGame(GameSession gameSession) {
        gameSession.resetGame();
    }
}
