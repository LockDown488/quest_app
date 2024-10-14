import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.kopanev.spring.model.GameSession;

public class GameSessionTest {
    private GameSession gameSession;

    @BeforeEach
    public void setUp() {
        gameSession = new GameSession("Tester");
    }

    @Test
    public void testResetGame() {
        gameSession.makeChoice("Принять вызов");
        gameSession.makeChoice("Подняться на мостик");

        gameSession.resetGame();

        assertEquals("Твое сознание пробуждается в полной темноте, как будто ты был марионеткой, которой только что вернули контроль над телом. "
                + "Голова раскалывается от боли, но что-то важное ускользает — память. Ты не знаешь, кто ты и что здесь делаешь. "
                + "Вдруг, перед тобой появляется яркий свет, который словно режет пространство на части. Из него раздается голос — холодный, лишенный эмоций: "
                + "'Ты готов принять вызов?' Вопрос висит в воздухе, но ты не понимаешь, кто или что стоит за ним. "
                + "Откуда взялся этот голос? Принять ли вызов от неизвестного существа?", gameSession.getCurrentChoice());
        assertFalse(gameSession.getGameOver());
        assertEquals("Tester", gameSession.getPlayerName());
    }

    @Test
    public void testMakeChoiceAcceptChallenge() {
        gameSession.makeChoice("Принять вызов");
        assertEquals("Ты чувствуешь, как холодный ветер проникает в твое тело. "
                + "Приближаешься к мосту корабля. Но что-то кажется не так. "
                + "Поднимаешься на мостик к капитану или доверяешь своему инстинкту и отступаешь?", gameSession.getCurrentChoice());
        assertFalse(gameSession.getGameOver());
    }

    @Test
    public void testMakeChoiceRejectChallenge() {
        gameSession.makeChoice("Отклонить вызов");
        assertTrue(gameSession.getGameOver());
        assertEquals("Ты медленно отходишь назад, чувствуя, как что-то изменилось. "
                + "Но вдруг свет исчезает, и ты понимаешь, что сделал ошибку. "
                + "Это конец... Поражение.", gameSession.getGameResult());
    }

    @Test
    public void testGetAvailableChoicesAtStart() {
        assertEquals(2, gameSession.getAvailableChoices().size());
        assertTrue(gameSession.getAvailableChoices().contains("Принять вызов"));
        assertTrue(gameSession.getAvailableChoices().contains("Отклонить вызов"));
    }

    @Test
    public void testGetAvailableChoicesForSecretEnding() {
        gameSession.makeChoice("Отклонить вызов");
        gameSession.resetGame();
        gameSession.makeChoice("Отклонить вызов");
        gameSession.resetGame();
        gameSession.makeChoice("Отклонить вызов");

        assertTrue(gameSession.getAvailableChoices().contains("secret"));
    }
}
