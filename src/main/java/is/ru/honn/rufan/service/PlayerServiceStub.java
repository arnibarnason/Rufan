package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.domain.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by arnib on 20/09/15.
 * A service stub replicating a database.
 */
public class PlayerServiceStub implements PlayerService
{
    private List<Player> playerList = new ArrayList<Player>();
    Logger log = Logger.getLogger(PlayerServiceStub.class.getName());

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

    public List<Player> getPlayersByTeam(Team team)
    {
        List<Player> teamPlayers = new ArrayList<Player>();
        for (Player p : playerList)
        {
            if (p.getTeamId() == team.getTeamId())
            {
                teamPlayers.add(p);
            }
        }
        return teamPlayers;
    }

    public int addPlayer(Player player) throws ServiceException
    {
        if(player.getFirstName() == null)
        {
            player.setFirstName("");
        }

        if(player.getTeamId() == 0 ||
                player.getLastName() == null ||
                player.getLastName().isEmpty())
        {
            String msg = "Invalid parameters for adding player";
            throw new ServiceException(msg);
        }

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
        log.info("New player added.");
        return playerList.size() - 1;
    }
}