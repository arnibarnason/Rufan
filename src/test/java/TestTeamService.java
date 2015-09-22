/**
 * JUnit tests for teamService
 */

import is.ru.honn.rufan.domain.League;
import is.ru.honn.rufan.domain.Season;
import is.ru.honn.rufan.domain.Team;
import is.ru.honn.rufan.domain.Venue;
import is.ru.honn.rufan.service.TeamService;
import is.ru.honn.rufan.service.ServiceException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:app-test-stub.xml")
public class TestTeamService extends TestCase {

    @Autowired
    private TeamService service;

    Season season;
    League league;
    Venue venue;

    /**
     * Create a venue, season and league and set their attributes.
     */
    @Before
    public void setup() {
        venue = new Venue();
        venue.setName("Anfield");
        venue.setCity("Liverpool");
        venue.setVenueId(1);

        season = new Season();
        season.setIsActive(true);
        season.setName("EPL 2015");
        season.setSeason(1);

        league = new League();
        league.setName("English premier league");
        league.setDisplayName("English Premier League");
        league.setAbbreviation("EPL");
        league.setLeagueId(1);
        league.setSeason(season);
    }

    /**
     * Test if adding a valid team works correctly.
     * @throws ServiceException
     */
    @Test
    public void testAddValidTeam() throws ServiceException
    {
        Team testTeam = new Team(1, "Liverpool", "LIV", "Liverpool", venue);
        Team getTeam = null;

        service.addTeam(league.getLeagueId(), testTeam);

        List <Team> getTeamList = service.getTeams(league.getLeagueId());

        for(Team t: getTeamList)
        {
            if (t.getTeamId() == testTeam.getTeamId())
            {
                getTeam = t;
            }
        }
        assertSame(getTeam, testTeam);
    }

    /**
     * Test if adding a team with no name results in exception thrown.
     * @throws ServiceException
     */
    @Test(expected = ServiceException.class)
    public void testAddTeamNoTeamName() throws ServiceException
    {
        Team testTeam = new Team(2, "Manchester", "MANU", "", venue);

        service.addTeam(league.getLeagueId(), testTeam);
    }

    /**
     * Test if adding team with no venue results in exception thrown.
     * @throws ServiceException
     */
    @Test(expected = ServiceException.class)
    public void testAddTeamNoVenue() throws ServiceException
    {
        Team testTeam = new Team(3, "Somewhere", "WBA", "West Bromwich Albion", null);

        service.addTeam(league.getLeagueId(), testTeam);
    }

    /**
     * Test if adding team with no abbreviation results in exception thrown.
     * @throws ServiceException
     */
    @Test(expected = ServiceException.class)
    public void testAddTeamNoAbbreviation() throws ServiceException
    {
        Team testTeam = new Team(4, "Chelsea", "", "Chelsea", venue);

        service.addTeam(league.getLeagueId(), testTeam);
    }

    /**
     * Test if adding a team with ID: 0 results in exception thrown.
     * @throws ServiceException
     */
    @Test(expected = ServiceException.class)
    public void testAddTeamZeroLeagueID() throws ServiceException
    {
        Team testTeam = new Team(0, "Manchester", "MANU", "Manchester United", null);

        service.addTeam(league.getLeagueId(), testTeam);

    }

    /**
     * Test if adding a team that has already been added results in exception thrown.
     * @throws ServiceException
     */
    @Test(expected = ServiceException.class)
    public void testAddTeamThatExists() throws ServiceException
    {
        Team testTeam = new Team(6, "Manchester", "MANU", "Manchester United", null);

        service.addTeam(league.getLeagueId(), testTeam);
        service.addTeam(league.getLeagueId(), testTeam);

    }

    /**
     * Test if getting a team that does not exist returns null.
     * @throws ServiceException
     */
   @Test
    public void testGetTeamThatNotExists() throws ServiceException
    {
        assertEquals(null, service.getTeams(100000));
    }
}