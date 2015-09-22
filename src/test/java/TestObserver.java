import is.ru.honn.rufan.domain.*;
import is.ru.honn.rufan.observers.Observer;
import is.ru.honn.rufan.process.PlayerImportProcess;
import is.ru.honn.rufan.service.PlayerServiceStub;
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
public class TestObserver extends TestCase
{
    @Autowired
    private Observer observer;

    @Before
    public void setup() {

    }

    @Test
    public void testCountOfNotifications()
    {
        final List<Position> positions = new ArrayList<Position>() {{
            add(new Position(1, "DEFENDER", "D", 0));
        }};
        Country country = new Country(1, "Denmark", "DK");
        final Player testPlayer = new Player(1, "Gunnar", "Kjartansson", 193, 97, null, country, 1, positions);

        PlayerImportProcess playerImportProcess = new PlayerImportProcess();
        playerImportProcess.setService(new PlayerServiceStub());
        playerImportProcess.addObserver(new Observer() {
            public void update(Object object) {
                assertEquals(testPlayer, object);
            }
        });

        playerImportProcess.read(1, testPlayer);
    }
}