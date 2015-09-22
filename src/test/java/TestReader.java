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
 * JUnit tests for testing the reader
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

    /**
     * Test if readerFactory reads the reader.xml correctly
     * @throws ReaderException
     */
    @Test
    public void testReadXmlWithPlayerReader() throws ReaderException {
        String typeOfReader = "PlayerReader";

        Reader reader = readerFactory.getReader(typeOfReader);

        assertSame(PlayerReader.class, reader.getClass());
    }

    /**
     * Test if getReader function returns correct type of reader, read from a bean inside
     * reader.xml file.
     * @throws ReaderException
     */
    @Test
    public void testReadXmlWithTeamReader() throws ReaderException {
        String typeOfReader = "TeamReader";

        Reader reader = null;
        reader = readerFactory.getReader(typeOfReader);

        assertSame(TeamReader.class, reader.getClass());
    }

    /**
     * Test if trying to get an non-existing readerType results in an exception thrown.
     * @throws ReaderException
     */
    @Test(expected = ReaderException.class)
    public void testReadXmlWithWrongReader() throws ReaderException {
        String typeOfReader = "WrongReader";

        Reader reader = readerFactory.getReader(typeOfReader);

        assertSame(TeamReader.class, reader.getClass());
    }

    /**
     * Test if an invalid URI results in an exception thrown.
     * @throws Exception
     */
    @Test(expected = ReaderException.class)
    public void testAbstractReaderWithWrongURI() throws Exception
    {
        String typeOfReader = "PlayerReader";

        Reader reader = readerFactory.getReader(typeOfReader);
        reader.setURI("NOURI");

        reader.read();
    }

    /**
     * Test if setting no readHandler results in an exception thrown.
     * @throws Exception
     */
    @Test(expected = ReaderException.class)
    public void testAbstractReaderNoHandler() throws Exception
    {
        String typeOfReader = "PlayerReader";

        Reader reader = readerFactory.getReader(typeOfReader);
        reader.setURI("http://olafurandri.com/honn/players.json");

        reader.read();
    }

    /**
     * Test if the number of lines read match the number of players in the
     * file players.json, they are 582.
     * @throws ReaderException
     */
    @Test
    public void testNumberOfLinesRead() throws ReaderException {
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