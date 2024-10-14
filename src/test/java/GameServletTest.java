import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kopanev.spring.model.GameSession;
import ru.kopanev.spring.servlet.GameServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GameServletTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @InjectMocks
    private GameServlet gameServlet;

    @BeforeEach
    public void setUp() {
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        when(request.getParameter("choice")).thenReturn("Принять вызов");

        GameSession gameSession = new GameSession("Tester");
        when(request.getSession()).thenReturn(session);

        gameServlet.service(request, response);

        assertEquals("Ты чувствуешь, как холодный ветер проникает в твое тело. "
                + "Приближаешься к мосту корабля. Но что-то кажется не так. "
                + "Поднимаешься на мостик к капитану или доверяешь своему инстинкту и отступаешь?", gameSession.getCurrentChoice());

        verify(session).setAttribute(eq("gameSession"), any(GameSession.class));
    }

    @Test
    public void testRedirect() throws ServletException, IOException {
        gameServlet.service(request, response);

        verify(response).sendRedirect(request.getContextPath() + "/welcome");
    }
}
