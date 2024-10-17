package ru.kopanev.spring.servlet;

import ru.kopanev.spring.model.GameSession;
import ru.kopanev.spring.service.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");

        if (gameSession == null) {
            String playerName = req.getParameter("playerName");
            gameSession = GameService.initializeGame(session, playerName);
            session.setAttribute("gameSession", gameSession);
        }

        String choice = req.getParameter("choice");

        if (choice != null) {
            if ("secret".equals(choice)) {
                gameSession.setSecretEndingFound(true);
            }
            GameService.makeChoice(gameSession, choice);
        }

        if (req.getParameter("restart") != null) {
            GameService.restartGame(gameSession);
        }

        String userIp = req.getRemoteAddr();

        if (userIp.equals("0:0:0:0:0:0:0:1")) {
            userIp = "127.0.0.1";
        }

        req.setAttribute("userIp", userIp);

        req.setAttribute("nameLength", gameSession.getPlayerName().length());

        req.getRequestDispatcher("/WEB-INF/jsp/game.jsp").forward(req, resp);
    }
}
