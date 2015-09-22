package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.domain.Team;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by arnib on 20/09/15.
 * A service stub replicating a database.
 */
public class PlayerServiceStub implements PlayerService
{
    private List<Player> playerList = new ArrayList<Player>();
    Logger log = Logger.getLogger(PlayerServiceStub.class.getName());
    // Instance of TeamServiceStub to be able to use getTeamBtAbbreviation
    private TeamServiceStub teamServiceStub;

    /**
     * Method to add a player to the list of players.
     * @param player is the player to add.
     * @return the list index of the player added.
     * @throws ServiceException
     */
    public int addPlayer(Player player) throws ServiceException
    {
        // Set FirstName to empty string if null, because of a flaw in the
        // player.json file (Assignment update 21/9).
        if(player.getFirstName() == null)
        {
            player.setFirstName("");
        }
        // Check for invalid properties of a player.
        if(player.getTeamId() == 0 ||
                player.getLastName() == null ||
                player.getLastName().isEmpty())
        {
            String msg = "Invalid parameters for adding player";
            throw new ServiceException(msg);
        }
        // Loop through the list of players to check if the player exists,
        // if so throw exception, else add the player to the list.
        for (Player p : playerList)
        {
            if (p.getPlayerId() == player.getPlayerId())
            {
                String msg = "Player with playerID: '" + player.getPlayerId() + "' already exists.";
                log.info(msg);
                throw new ServiceException(msg);
            }
        }

        playerList.add(player);
        String msg = "New player added";
        log.info(msg);

        return playerList.size() - 1;
    }

    /**
     * Gets a player with the given playerID.
     * @param playerId The ID of the desired player.
     * @return The player with the playerID, if not exists, return null.
     */
    public Player getPlayer(int playerId)
    {
        for (Player p : playerList)
        {
            if (p.getPlayerId() == playerId)
            {
                return p;
            }
        }
        // Player requested doesn't exists
        String msg = "Player with playerID: '" + playerId + "' does not exists.";
        log.info(msg);
        return null;
    }

    /**
     * Gets a list of players that play for the team with teamID.
     * @param teamId The ID of the desired team.
     * @return The list of players, empty if no player found.
     */
    public List<Player> getPlayers(int teamId)
    {
        List<Player> players = new ArrayList<Player>();
        for(Player p : playerList)
        {
            if(p.getTeamId() == teamId)
            {
                players.add(p);
            }
        }
        return players;
    }

    /**
     * Get all players that play for a given team.
     * @param abbreviation The team abbreviation
     * @return A list of players.
     */
    public List<Player> getPlayersByTeam(String abbreviation)
    {
        List<Player> players = new ArrayList<Player>();
        Team team;
        team = teamServiceStub.getTeamByAbbreviation(abbreviation);

        for (Player p : playerList)
        {
            if (p.getTeamId() == team.getTeamId())
            {
                players.add(p);
            }
        }
        return players;
    }
}