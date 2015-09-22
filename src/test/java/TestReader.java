import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.process.PlayerImportProcess;
import is.ru.honn.rufan.reader.*;
import is.ru.honn.rufan.service.PlayerServiceStub;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnib on 20/09/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
public class TestReader extends TestCase
{
    @Autowired
    private ReaderFactory readerFactory;

    @Before
    public void setup() {

    }

    @Test
    public void testReadXmlWithPlayerReader()
    {
        String typeOfReader = "PlayerReader";

        Reader reader = readerFactory.getReader(typeOfReader);

        assertSame(PlayerReader.class, reader.getClass());
    }

    @Test
    public void testReadXmlWithTeamReader()
    {
        String typeOfReader = "TeamReader";

        Reader reader = readerFactory.getReader(typeOfReader);

        assertSame(TeamReader.class, reader.getClass());
    }

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void testReadXmlWithWrongReader()
    {
        String typeOfReader = "WrongReader";

        Reader reader = readerFactory.getReader(typeOfReader);

        assertSame(TeamReader.class, reader.getClass());
    }

    @Test(expected = ReaderException.class)
    public void testAbstractReaderWithWrongURI() throws Exception
    {
        String typeOfReader = "PlayerReader";

        Reader reader = readerFactory.getReader(typeOfReader);
        reader.setURI("NOURI");

        reader.read();
    }


    @Test(expected = ReaderException.class)
    public void testAbstractReaderNoHandler() throws Exception
    {
        String typeOfReader = "PlayerReader";

        Reader reader = readerFactory.getReader(typeOfReader);
        reader.setURI("http://olafurandri.com/honn/players.json");

        reader.read();
    }

    @Test
    public void testNumberOfLinesRead()
    {
        List<Player> players = new ArrayList<Player>();
        String typeOfReader = "PlayerReader";

        Reader reader = readerFactory.getReader(typeOfReader);
        reader.setURI("http://olafurandri.com/honn/players.json");
        PlayerImportProcess playerImportProcess = new PlayerImportProcess();
        reader.setReadHandler(playerImportProcess);
        playerImportProcess.setService(new PlayerServiceStub());

        try
        {
            players = (List<Player>) reader.read();
        } catch(ReaderException r)
        {
            assertTrue(false);
        }
        assertEquals(582, players.size());
    }
}