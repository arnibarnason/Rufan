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
    Logger log = Logger.getLogger(TeamServiceStub.class.getName());

    public TeamServiceStub() {
        League league = new League();
        // English premier leagueID is 39 in the data.
        league.setLeagueId(39);
        leagueList.add(league);
    }

    public int addTeam(int leagueId, Team team) throws ServiceException
    {
        if(leagueId == 0){
            String msg = "A league with ID: 0 does not exist and is not allowed";
            log.info(msg);
            throw new ServiceException(msg);
        }

        if(team.getTeamId() == 0 ||
                team.getAbbreviation() == null ||
                team.getAbbreviation().isEmpty() ||
                team.getVenue() == null ||
                team.getDisplayName() == null ||
                team.getDisplayName().isEmpty())
        {
            String msg = "Invalid parameters to addTeam";
            log.info(msg);
            throw  new ServiceException(msg);
        }

        League l = getLeague(leagueId);

        if (l == null)
        {
            l = new League();
            l.setLeagueId(leagueId);
            l.setName("TmpLeague");
            l.setDisplayName("TmpLeague");
            l.setAbbreviation("Tmp");
            l.setSeason(new Season());

            l.getSeason().setName("TmpSeason");
            l.getSeason().setSeason(1);
            l.getSeason().setIsActive(false);

            leagueList.add(l);
        }

        for (Team t : l.getSeason().getTeams())
        {
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

    private League getLeague(int leagueId)
    {
        for (League l : leagueList)
        {
            if (l.getLeagueId() == leagueId)
            {
                return l;
            }
        }
        return null;
    }

    public List<Team> getTeams(int leagueId)
    {
        League league = getLeague(leagueId);
        if(league != null){
            return league.getSeason().getTeams();
        }
        return null;
    }
}