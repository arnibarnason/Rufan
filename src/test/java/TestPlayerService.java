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

import javax.xml.ws.Service;
import java.util.*;


import java.util.logging.Logger;

/**
 * Created by arnib on 20/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
public class TestPlayerService extends TestCase
{
    Logger log = Logger.getLogger(TestPlayerService.class.getName());

    @Autowired
    private PlayerService service;

    @Before
    public void setup()
    {

    }

    //@Test(expected = ServiceException.class)
    @Test
    public void testPlayer() throws ServiceException {

        //     public Position(int positionId, String name, String abbreviation, int sequence)
        Position pos = new Position(1, "midfielder", "mid", 3);

        final List<Position> PLAYER1_POSITIONS = new ArrayList<Position>() {{
            add(new Position(1, "Midfielder", "M", 0));
        }};

        Country country = new Country(1, "Denmark", "DK");
        Player testPlayer = new Player(0, "Arni", "Arnason", 193, 97, newDate(1995, 11, 12), country, 1, PLAYER1_POSITIONS);
        Player testPlayer2 = new Player(1, "Arni", "Arnason", 193, 97, null, null, 1, null);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer2);
        Player playerNew = service.getPlayer(0);
        Player playerNew2 = service.getPlayer(1);
        assertSame(playerNew, testPlayer);
        assertSame(playerNew2, testPlayer2);

    }

    @Test(expected = ServiceException.class)
    public void testAddPlayerThatFails() throws ServiceException
    {
        Player testPlayer = new Player(4, "Arni", "Arnason", 193, 97, null, null, 1, null);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer);
    }

    @Test
    public void testGetPlayerThatFails() throws ServiceException
    {
        assertEquals(null, service.getPlayer(100000));
    }

    protected Date newDate(int year, int month, int date)
    {
        Calendar cal = new GregorianCalendar();
        cal.set(year, month, date);
        return cal.getTime();
    }
}


