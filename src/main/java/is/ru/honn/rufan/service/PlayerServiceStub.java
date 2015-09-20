package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by arnib on 20/09/15.
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
        return null;
    }

    public List<Player> getPlayers(int teamId)
    {
        return playerList;
    }

    public int addPlayer(Player player) throws ServiceException
    {
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
