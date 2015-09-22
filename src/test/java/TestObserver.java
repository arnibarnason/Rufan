//import is.ru.honn.rufan.domain.*;
//import is.ru.honn.rufan.service.ServiceException;
//import junit.framework.TestCase;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import java.util.*;
//
///**
// * Created by arnib on 20/09/15.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:app-test-stub.xml")
//public class TestObserver extends TestCase
//{
//    @Autowired
//    private Observer observer;
//
//    Venue venue;
//    Team team;
//
//    @Before
//    public void setup() {
//        venue = new Venue();
//        venue.setName("Anfield");
//        venue.setCity("Liverpool");
//        venue.setVenueId(1);
//
//        team = new Team();
//        team.setTeamId(1);
//        team.setAbbreviation("LIV");
//        team.setDisplayName("Liverpool");
//        team.setLocation("Liverpool");
//        team.setVenue(venue);
//    }
//    // This test covers both adding a valid Player and getting a Player that exists
//    @Test
//    public void testAddValidPlayer() throws ServiceException {
//
//        final List<Position> positions = new ArrayList<Position>() {{
//            add(new Position(1, "DEFENDER", "D", 0));
//        }};
//
//        Country country = new Country(1, "Denmark", "DK");
//        Player testPlayer = new Player(1, "Gunnar", "Kjartansson", 193, 97, null, country, team.getTeamId(), positions);
//        Player testPlayer2 = new Player(2, "Arni", "Arnason", 193, 97, null, country, team.getTeamId(), positions);
//
//        service.addPlayer(testPlayer);
//        service.addPlayer(testPlayer2);
//
//        Player getPlayer = service.getPlayer(testPlayer.getPlayerId());
//        Player getPlayer2 = service.getPlayer(testPlayer2.getPlayerId());
//
//        assertSame(getPlayer, testPlayer);
//        assertSame(getPlayer2, testPlayer2);
//    }
//
//    @Test(expected = ServiceException.class)
//    public void testAddPlayerThatExists() throws ServiceException
//    {
//        Player testPlayer = new Player(3, "Arni", "Arnason", 193, 97, null, null, 1, null);
//
//        service.addPlayer(testPlayer);
//        service.addPlayer(testPlayer);
//    }
//    // FirstName can be empty or null (update on assignment description 21/9)
//    @Test
//    public void testAddPlayerNoFirstName() throws ServiceException
//    {
//        final List<Position> positions = new ArrayList<Position>() {{
//            add(new Position(1, "STRIKER", "S", 0));
//        }};
//        Country country = new Country(2, "Iceland", "IS");
//
//        Player testPlayer = new Player(4, "", "Kjartansson", 181, 83, null, country, team.getTeamId(), positions);
//        Player testPlayer2 = new Player(5, null, "Ingason", 193, 94, null, country, team.getTeamId(), positions);
//
//        service.addPlayer(testPlayer);
//        service.addPlayer(testPlayer2);
//
//        Player getPlayer = service.getPlayer(testPlayer.getPlayerId());
//        Player getPlayer2 = service.getPlayer(testPlayer2.getPlayerId());
//
//        assertSame(getPlayer, testPlayer);
//        assertSame(getPlayer2, testPlayer2);
//    }
//
//    @Test(expected = ServiceException.class)
//    public void testAddPlayerNoLastName() throws ServiceException
//    {
//        final List<Position> positions = new ArrayList<Position>() {{
//            add(new Position(1, "STRIKER", "S", 0));
//        }};
//        Country country = new Country(2, "Iceland", "IS");
//
//        Player testPlayer = new Player(6, "Gunnar", "", 181, 83, null, country, team.getTeamId(), positions);
//        Player testPlayer2 = new Player(7, "Gunnar", null, 181, 83, null, country, team.getTeamId(), positions);
//
//        service.addPlayer(testPlayer);
//        service.addPlayer(testPlayer2);
//    }
//
//    @Test(expected = ServiceException.class)
//    public void testAddPlayerZeroTeamID() throws ServiceException
//    {
//        final List<Position> positions = new ArrayList<Position>() {{
//            add(new Position(1, "STRIKER", "S", 0));
//        }};
//        Country country = new Country(2, "Iceland", "IS");
//
//        Player testPlayer = new Player(6, "Gunnar", "Kjartansson", 181, 83, null, country, 0, positions);
//
//        service.addPlayer(testPlayer);
//    }
//
//    @Test
//    public void testGetPlayerThatFails() throws ServiceException
//    {
//        assertEquals(null, service.getPlayer(100000));
//    }
//}