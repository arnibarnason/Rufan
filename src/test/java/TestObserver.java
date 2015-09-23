import is.ru.honn.rufan.domain.*;
import is.ru.honn.rufan.observers.Observer;
import is.ru.honn.rufan.process.PlayerImportProcess;
import is.ru.honn.rufan.service.PlayerService;
import is.ru.honn.rufan.service.PlayerServiceStub;
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
 * JUnit test for observer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
public class TestObserver extends TestCase
{
    @Autowired
    private PlayerService service;

    @Before
    public void setup() {

    }

    /**
     * Test the observer pattern. Make a new player and see if the observer is notified that
     * the players has been added.
     */
    @Test
    public void testObserver() throws ServiceException {
        final List<Position> positions = new ArrayList<Position>() {{
            add(new Position(1, "DEFENDER", "D", 0));
        }};
        Country country = new Country(1, "Denmark", "DK");
        final Player testPlayer = new Player(1, "Gunnar", "Kjartansson", 193, 97, null, country, 1, positions);

        service.addObserver(new Observer() {
            public void update(Object object) {
                assertEquals(testPlayer, object);
            }
        });
        service.addPlayer(testPlayer);

    }
}