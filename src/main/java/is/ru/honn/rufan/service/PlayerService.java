package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.domain.Team;
import javafx.beans.Observable;

import java.util.List;

/**
 * Created by arnib on 20/09/15.
 */
public interface PlayerService
{
    Player getPlayer(int playerId);
    List<Player> getPlayers(int teamId);
    List<Player> getPlayersByTeam(Team team);
    int addPlayer(Player player) throws ServiceException;
}