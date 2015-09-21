/**
 * Created by gunnarok on 20.9.15.
 */

import is.ru.honn.rufan.domain.Team;
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

    @Before
    public void setup() {}

    @Test
    public void testAddTeam() throws ServiceException
    {
        Team testTeam = new Team(0, "Liverpool", "LIV", "Liverpool", null);
        Team getTeam = null;

        service.addTeam(39, testTeam);

        List <Team> getTeamList = service.getTeams(39);

        for(Team t: getTeamList)
        {
            if (t.getTeamId() == testTeam.getTeamId())
            {
                getTeam = t;
            }
        }
        assertSame(getTeam, testTeam);
    }

    @Test(expected = ServiceException.class)
    public void testAddTeamThatFails() throws ServiceException
    {
        Team testTeam = new Team(1, "Manchester", "MANU", "Manchester United", null);

        service.addTeam(39, testTeam);
        service.addTeam(39, testTeam);
    }

    @Test(expected = ServiceException.class)
    public void testAddTeamNoLeaguID() throws ServiceException
    {
        Team testTeam = new Team(2, "Manchester", "MANU", "Manchester United", null);

        service.addTeam(0, testTeam);

    }

   /*@Test
    public void testGetTeamThatFails() throws ServiceException
    {
        assertEquals(null, service.get);
    }*/

}