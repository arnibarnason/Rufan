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
    int addPlayer(Player player) throws ServiceException;
    List<Player> getPlayersByTeam(String abbreviation);
}