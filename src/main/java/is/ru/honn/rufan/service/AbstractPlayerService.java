package is.ru.honn.rufan.service;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.observers.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arnib on 23/09/15.
 */
public abstract class AbstractPlayerService implements PlayerService
{
    private List<Observer> observerList = new ArrayList<Observer>();

    public abstract Player getPlayer(int playerId);

    public abstract List<Player> getPlayers(int teamId);

    public abstract int addPlayer(Player player) throws ServiceException;

    public abstract List<Player> getPlayersByTeam(String abbreviation);

    /**
     * Adds an observer to the list of observers
     * @param o The observer to be added to the list
     */
    public void addObserver(Observer o)
    {
        observerList.add(o);
    }

    /**
     * Removes an observer from the list of observers.
     * @param o The observer to be removed
     */
    public void removeObserver(Observer o)
    {
        observerList.remove(o);
    }

    /**
     * Send notification to all observers in the observer list
     * @param object Object created
     */
    public void notifyObservers(Object object)
    {
        for(Observer observer : observerList)
        {
            observer.update(object);
        }
    }
}
