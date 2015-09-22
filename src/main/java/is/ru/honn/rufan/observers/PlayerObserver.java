package is.ru.honn.rufan.observers;

import is.ru.honn.rufan.domain.Player;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by arnib on 22/09/15.
 */
public class PlayerObserver implements Observer
{
    public void update(Observable o, Object arg)
    {
        Player p = (Player) arg;
        System.out.println("Name: " + p.getFirstName() + " " + p.getLastName() + ", TeamId: " + p.getTeamId());
    }
}