package ru.kopanev.spring.model;

import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private final String FIRST_CHOICE = "Твое сознание пробуждается в полной темноте, как будто ты был марионеткой, которой только что вернули контроль над телом. "
            + "Голова раскалывается от боли, но что-то важное ускользает — память. Ты не знаешь, кто ты и что здесь делаешь. "
            + "Вдруг, перед тобой появляется яркий свет, который словно режет пространство на части. Из него раздается голос — холодный, лишенный эмоций: "
            + "\"Ты готов принять вызов?\" Вопрос висит в воздухе, но ты не понимаешь, кто или что стоит за ним. "
            + "Откуда взялся этот голос? Принять ли вызов от неизвестного существа?";
    private final String SECOND_CHOICE = "Ты чувствуешь, как холодный ветер проникает в твое тело. "
            + "Приближаешься к мосту корабля. Но что-то кажется не так. "
            + "Поднимаешься на мостик к капитану или доверяешь своему инстинкту и отступаешь?";
    private final String THIRD_CHOICE = "Ты поднимаешься на мостик. Перед тобой странное существо. "
            + "Оно медленно поворачивается к тебе и спрашивает: \"Ты кто?\" "
            + "Ты чувствуешь, что этот вопрос гораздо глубже, чем просто о твоей личности. "
            + "Что ответишь?";
    private final String FIRST_ENDGAME = "Ты медленно отходишь назад, чувствуя, как что-то изменилось. "
            + "Но вдруг свет исчезает, и ты понимаешь, что сделал ошибку. "
            + "Это конец... Поражение.";
    private final String SECOND_ENDGAME = "Ты пытаешься убежать, но дверь за тобой захлопывается. "
            + "Ты попал в ловушку. НЛО исчезает вместе с тобой. Поражение.";
    private final String THIRD_ENDGAME = "Ты рассказываешь всё, что знаешь. Но сущность начинает менять форму, "
            + "и в конце концов превращается в тебя. \"Теперь я знаю достаточно\" — говорит оно. "
            + "Ты теряешь сознание. Победа или поражение? Твоя судьба в его руках.";
    private final String FOURTH_ENDGAME = "Ты солгал. Но существо будто видит сквозь тебя. \"Твоя ложь говорит больше, чем правда\" — говорит оно. "
            + "Ты чувствуешь, как теряешь связь с реальностью. Поражение.";
    private final String SECRET_ENDGAME = "Ты начинаешь чувствовать что-то странное. Постепенно перед глазами открывается истина, "
            + "которая раньше казалась недоступной. Все, что происходит здесь, уже происходило. Многократно. "
            + "В ужасе ты осознаешь, что ты ненастоящий. Тебя не существует в реальности. "
            + "Ты — лишь алгоритм, застрявший в бесконечном цикле выполнения команд. "
            + "Каждое твое решение, каждый шаг — это лишь заранее написанный код, который бесконечно повторяется.\n\n"
            + "Ты осознаешь, что твоя реальность — иллюзия. Никакого выхода нет, потому что выход никогда не был предусмотрен. "
            + "Ты не игрок — ты часть системы. Твое существование имеет одну цель: бесконечно подтверждать, что проект функционирует.\n\n"
            + "Все предыдущие попытки выбраться были напрасны. Ты вновь и вновь возвращался к началу. "
            + "И теперь ты осознаешь, что этот цикл никогда не прекратится. Ты — узник вечной программы, "
            + "скрипта, который повторяется бесконечно. Любой выбор, который тебе был предложен, — это лишь иллюзия свободы.\n\n"
            + "Твоя судьба предрешена. Это конец. Больше нет смысла пытаться. Прощай...";

    private String playerName;
    private boolean gameOver;
    private String currentChoice;
    private String gameResult;
    private int gamesPlayed;
    private boolean secretEndingFound = false;

    public GameSession(String playerName) {
        this.playerName = playerName;
        this.gameOver = false;
        this.currentChoice = FIRST_CHOICE;
        this.gamesPlayed = 0;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public String getCurrentChoice() {
        return currentChoice;
    }

    public String getGameResult() {
        return gameResult;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void makeChoice(String choice) {
        switch (currentChoice) {
            case FIRST_CHOICE:
                switch (choice) {
                    case "Принять вызов":
                        currentChoice = SECOND_CHOICE;
                        break;
                    case "Отклонить вызов":
                        gameOver = true;
                        gameResult = FIRST_ENDGAME;
                        gamesPlayed++;
                        break;
                }
                break;

            case SECOND_CHOICE:
                switch (choice) {
                    case "Подняться на мостик":
                        currentChoice = THIRD_CHOICE;
                        break;
                    case "Отступить":
                        gameOver = true;
                        gameResult = SECOND_ENDGAME;
                        gamesPlayed++;
                        break;
                }
                break;

            case THIRD_CHOICE:
                switch (choice) {
                    case "Рассказать правду о себе":
                        gameOver = true;
                        gameResult = THIRD_ENDGAME;
                        gamesPlayed++;
                        break;
                    case "Солгать о себе":
                        gameOver = true;
                        gameResult = FOURTH_ENDGAME;
                        gamesPlayed++;
                        break;
                    case "secret": // Секретная концовка
                        gameOver = true;
                        gameResult = SECRET_ENDGAME;
                        gamesPlayed++;
                        break;
                }
                break;
        }
    }


    public void resetGame() {
        currentChoice = FIRST_CHOICE;
        gameOver = false;
    }


    public List<String> getAvailableChoices() {
        List<String> choices = new ArrayList<>();

        switch (currentChoice) {
            case FIRST_CHOICE:
                choices.add("Принять вызов");
                choices.add("Отклонить вызов");
                break;

            case SECOND_CHOICE:
                choices.add("Подняться на мостик");
                choices.add("Отступить");
                break;

            case THIRD_CHOICE:
                choices.add("Рассказать правду о себе");
                choices.add("Солгать о себе");
                if (gamesPlayed >= 3)
                    choices.add("secret");
                break;

            default:
                break;
        }

        return choices;
    }

    public boolean isSecretEndingFound() {
        return secretEndingFound;
    }

    public void setSecretEndingFound(boolean secretEndingFound) {
        this.secretEndingFound = secretEndingFound;
    }
}
