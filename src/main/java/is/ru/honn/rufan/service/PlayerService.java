package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.observers.Subject;

import java.util.List;

/**
 * Interface for player service
 */
public interface PlayerService extends Subject
{
    Player getPlayer(int playerId);
    List<Player> getPlayers(int teamId);
    int addPlayer(Player player) throws ServiceException;
    List<Player> getPlayersByTeam(String abbreviation);
}