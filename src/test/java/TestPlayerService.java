import is.ru.honn.rufan.domain.Country;
import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.domain.Position;
import is.ru.honn.rufan.service.PlayerService;
import is.ru.honn.rufan.service.ServiceException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;

/**
 * Created by arnib on 20/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
public class TestPlayerService extends TestCase
{
    @Autowired
    private PlayerService service;

    @Before
    public void setup() {}

    @Test
    public void testAddPlayer() throws ServiceException {

        final List<Position> PLAYER1_POSITIONS = new ArrayList<Position>() {{
            add(new Position(1, "DEFENDER", "D", 0));
        }};

        Country country = new Country(1, "Denmark", "DK");
        Player testPlayer = new Player(0, "Gunnar", "Kjartansson", 193, 97, null, country, 1, PLAYER1_POSITIONS);
        Player testPlayer2 = new Player(1, "Arni", "Arnason", 193, 97, null, null, 1, null);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer2);
        Player getPlayer = service.getPlayer(testPlayer.getPlayerId());
        Player getPlayer2 = service.getPlayer(testPlayer2.getPlayerId());
        assertSame(getPlayer, testPlayer);
        assertSame(getPlayer2, testPlayer2);
    }

    @Test(expected = ServiceException.class)
    public void testAddPlayerThatFails() throws ServiceException
    {
        Player testPlayer = new Player(2, "Arni", "Arnason", 193, 97, null, null, 1, null);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer);
    }

    @Test
    public void testGetPlayerThatExists() throws ServiceException
    {
        final List<Position> PLAYER1_POSITIONS = new ArrayList<Position>() {{
            add(new Position(1, "STRIKER", "S", 0));
        }};

        Country country = new Country(2, "Iceland", "IS");
        Player testPlayer = new Player(3, "Gunnar", "Kjartansson", 181, 83, null, country, 1, PLAYER1_POSITIONS);
        Player testPlayer2 = new Player(4, "Sverrir", "Ingason", 193, 94, null, null, 1, null);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer2);

        Player getPlayer = service.getPlayer(testPlayer.getPlayerId());
        Player getPlayer2 = service.getPlayer(testPlayer2.getPlayerId());

        assertSame(getPlayer, testPlayer);
        assertSame(getPlayer2, testPlayer2);
    }

    @Test
    public void testGetPlayerThatFails() throws ServiceException
    {
        assertEquals(null, service.getPlayer(100000));
    }
}


