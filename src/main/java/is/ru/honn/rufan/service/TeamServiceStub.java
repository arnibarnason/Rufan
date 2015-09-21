package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.League;
import is.ru.honn.rufan.domain.Season;
import is.ru.honn.rufan.domain.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by arnib on 20/09/15.
 * A service stub replicating a database.
 */
public class TeamServiceStub implements TeamService
{
    private List<League> leagueList = new ArrayList<League>();
    Logger log = Logger.getLogger(TeamServiceStub.class.getName());

    /**
     * Method to add a team to a certain league. A league owns a Season,
     * a Season owns a list of teams which is the target list to add teams to.
     * @param leagueId The ID of the league
     * @param team The team object to be added to the list
     * @return The list index of the team added.
     * @throws ServiceException if a team has insufficient properties.
     */
    public int addTeam(int leagueId, Team team) throws ServiceException
    {
        // We assume leagueID = 0 is invalid
        if(leagueId == 0){
            String msg = "A league with ID: 0 does not exist and is not allowed";
            log.info(msg);
            throw new ServiceException(msg);
        }
        // Check for invalid properties of a team.
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
        // If the league does not exist, create it and set it's properties.
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
        // Loop through list of teams, if the team already exists, throw exception.
        // Else add it to the list of teams of the Season of the league.
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

    /**
     * Helper function.
     * Gets the league with leagueID as it's ID.
     * @param leagueId The ID of the wanted league.
     * @return The league, if not exists, return null.
     */
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

    /**
     * Gets a list of teams from the league with leagueID as it's ID.
     * @param leagueId The ID of the league that owns the Season that has the wanted list
     *                 of teams.
     * @return The list of teams unless the league does not exist, then null.
     */
    public List<Team> getTeams(int leagueId)
    {
        League league = getLeague(leagueId);
        if(league != null){
            return league.getSeason().getTeams();
        }
        return null;
    }
}