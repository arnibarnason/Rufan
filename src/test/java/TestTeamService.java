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

        service.addTeam(0, testTeam);

        List <Team> getTeamList = service.getTeamsByAbbreviation("LIV");

        for(Team t: getTeamList)
        {
            if (t.getTeamId() == testTeam.getTeamId())
            {
                getTeam = t;
            }
        }
        assertSame(getTeam, testTeam);
    }


}
