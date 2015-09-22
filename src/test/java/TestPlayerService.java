import is.ru.honn.rufan.domain.*;
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
 * JUnit tests for playerService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
public class TestPlayerService extends TestCase
{
    @Autowired
    private PlayerService service;

    Venue venue;
    Team team;

    @Before
    public void setup() {
        venue = new Venue();
        venue.setName("Anfield");
        venue.setCity("Liverpool");
        venue.setVenueId(1);

        team = new Team();
        team.setTeamId(1);
        team.setAbbreviation("LIV");
        team.setDisplayName("Liverpool");
        team.setLocation("Liverpool");
        team.setVenue(venue);
    }

    /**
     * This test covers both adding a valid Player and getting a Player that exists.
     * Two players made and added, then retrieved again to see if they match the originial.
     * @throws ServiceException
     */
    @Test
    public void testAddValidPlayer() throws ServiceException {

        final List<Position> positions = new ArrayList<Position>() {{
            add(new Position(1, "DEFENDER", "D", 0));
        }};

        Country country = new Country(1, "Denmark", "DK");
        Player testPlayer = new Player(1, "Gunnar", "Kjartansson", 193, 97, null, country, team.getTeamId(), positions);
        Player testPlayer2 = new Player(2, "Arni", "Arnason", 193, 97, null, country, team.getTeamId(), positions);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer2);

        Player getPlayer = service.getPlayer(testPlayer.getPlayerId());
        Player getPlayer2 = service.getPlayer(testPlayer2.getPlayerId());

        assertSame(getPlayer, testPlayer);
        assertSame(getPlayer2, testPlayer2);
    }

    /**
     * Test if adding a player that already exists results in exception thrown.
     * @throws ServiceException
     */
    @Test(expected = ServiceException.class)
    public void testAddPlayerThatExists() throws ServiceException
    {
        Player testPlayer = new Player(3, "Arni", "Arnason", 193, 97, null, null, 1, null);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer);
    }

    /**
     * Test if adding a player with no firstName works correctly. Since the data had a flaw in it
     * there was an option to leave the firstName empty, if it is null, then set it to empty string.
     * @throws ServiceException
     */
    @Test
    public void testAddPlayerNoFirstName() throws ServiceException
    {
        final List<Position> positions = new ArrayList<Position>() {{
            add(new Position(1, "STRIKER", "S", 0));
        }};
        Country country = new Country(2, "Iceland", "IS");

        Player testPlayer = new Player(4, "", "Kjartansson", 181, 83, null, country, team.getTeamId(), positions);
        Player testPlayer2 = new Player(5, null, "Ingason", 193, 94, null, country, team.getTeamId(), positions);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer2);

        Player getPlayer = service.getPlayer(testPlayer.getPlayerId());
        Player getPlayer2 = service.getPlayer(testPlayer2.getPlayerId());

        assertSame(getPlayer, testPlayer);
        assertSame(getPlayer2, testPlayer2);
    }

    /**
     * Test if adding a player with no lastName results in exception thrown.
     * @throws ServiceException
     */
    @Test(expected = ServiceException.class)
    public void testAddPlayerNoLastName() throws ServiceException
    {
        final List<Position> positions = new ArrayList<Position>() {{
            add(new Position(1, "STRIKER", "S", 0));
        }};
        Country country = new Country(2, "Iceland", "IS");

        Player testPlayer = new Player(6, "Gunnar", "", 181, 83, null, country, team.getTeamId(), positions);
        Player testPlayer2 = new Player(7, "Gunnar", null, 181, 83, null, country, team.getTeamId(), positions);

        service.addPlayer(testPlayer);
        service.addPlayer(testPlayer2);
    }

    /**
     * Test if adding a player with teamID: 0 results in exception thrown.
     * @throws ServiceException
     */
    @Test(expected = ServiceException.class)
    public void testAddPlayerZeroTeamID() throws ServiceException
    {
        final List<Position> positions = new ArrayList<Position>() {{
            add(new Position(1, "STRIKER", "S", 0));
        }};
        Country country = new Country(2, "Iceland", "IS");

        Player testPlayer = new Player(6, "Gunnar", "Kjartansson", 181, 83, null, country, 0, positions);

        service.addPlayer(testPlayer);
    }

    /**
     * Test if getting an player that does not exist returns null.
     * @throws ServiceException
     */
    @Test
    public void testGetPlayerThatFails() throws ServiceException
    {
        assertEquals(null, service.getPlayer(100000));
    }
}