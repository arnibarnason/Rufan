package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Player;

import java.util.List;

/**
 * Created by arnib on 20/09/15.
 */
public class PlayerServiceStub implements PlayerService
{
    private List<Player> playerList;

    public Player getPlayer(int playerId)
    {
        for (int i = 0; i < playerList.size(); i++)
        {
            if (playerList.get(i).getPlayerId() == playerId)
            {
                return playerList.get(i);
            }
        }
        return null;
    }

    public List<Player> getPlayers(int teamId)
    {
        return playerList;
    }

    public int addPlayer(Player player) throws ServiceException
    {
        playerList.add(player);
        return player.getPlayerId();
    }
}
