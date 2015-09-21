package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.League;
import is.ru.honn.rufan.domain.Season;
import is.ru.honn.rufan.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by arnib on 20/09/15.
 */
public class TeamServiceStub implements TeamService
{
    private List<League> leagueList = new ArrayList<League>();
    private List<Team> teamList = new ArrayList<Team>();

    Logger log = Logger.getLogger(TeamServiceStub.class.getName());

    public TeamServiceStub() {
        League league = new League();
        // English premier leagueID is 39 in the data.
        league.setLeagueId(39);
        leagueList.add(league);
    }

    public int addTeam(int leagueId, Team team) throws ServiceException
    {
        for (League l : leagueList)
        {
            if(l.getLeagueId() == leagueId)
            {
                if(l.getSeason() == null)
                {
                    l.setSeason(new Season());
                }

                for (Team t : l.getSeason().getTeams()) {
                    if (t.getTeamId() == team.getTeamId())
                    {
                        String msg = "Team with teamId: '" + team.getTeamId() + "' already exists.";
                        log.info(msg);
                        throw new ServiceException(msg);
                    }
                }
                l.getSeason().addTeam(team);
                log.info("New team added.");
                return l.getSeason().getTeams().size() - 1;
            }
        }
        return 0;
    }

    public List<Team> getTeams(int leagueId)
    {
        for(League l : leagueList)
        {
            if(l.getLeagueId() == leagueId)
            {
                return l.getSeason().getTeams();
            }
        }
        return null;
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
