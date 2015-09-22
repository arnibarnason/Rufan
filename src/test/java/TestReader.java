import is.ru.honn.rufan.reader.PlayerReader;
import is.ru.honn.rufan.reader.Reader;
import is.ru.honn.rufan.reader.ReaderFactory;
import is.ru.honn.rufan.reader.TeamReader;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test(expected = Exception.class)
    public void testReadXmlWithWrongReader()
    {
        String typeOfReader = "TeamReader";

        Reader reader = readerFactory.getReader(typeOfReader);

        assertSame(TeamReader.class, reader.getClass());
    }

}
