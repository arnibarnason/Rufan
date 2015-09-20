package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Team;

import java.util.List;

/**
 * Created by arnib on 20/09/15.
 */
public class TeamServiceStub implements TeamService
{
    private List<Team> teamList;

    public int addTeam(int leagueId, Team team) throws ServiceException
    {
        teamList.add(team);
        return teamList.indexOf(team);
    }

    public List<Team> getTeams(int leagueId)
    {
        return null;
    }
}
