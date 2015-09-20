package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by arnib on 20/09/15.
 */
public class TeamServiceStub implements TeamService
{
    private List<Team> teamList = new ArrayList<Team>();
    Logger log = Logger.getLogger(TeamServiceStub.class.getName());

    public int addTeam(int leagueId, Team team) throws ServiceException
    {
        for (Team t : teamList)
        {
            if (t.getTeamId() == team.getTeamId())
            {
                String msg = "Team with teamId: '" + team.getTeamId() + "' already exists.";
                log.info(msg);
                throw new ServiceException(msg);
            }
        }

        teamList.add(team);
        return teamList.size() - 1;
    }

    public List<Team> getTeams(int leagueId)
    {
        return teamList;
    }

    public List<Team> getTeamsByAbbreviation(String abbreviation)
    {
        List<Team> tempList = new ArrayList<Team>();
        for (Team t : teamList)
        {
            if (t.getAbbreviation() == abbreviation)
            {
                tempList.add(t);
            }
        }

        return tempList;
    }
}
