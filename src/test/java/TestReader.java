import is.ru.honn.rufan.reader.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
