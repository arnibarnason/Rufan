package is.ru.honn.rufan.observers;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.domain.Position;

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
        System.out.print("PlayerID " + p.getPlayerId() + ", Name: " +
                p.getFirstName() + " " + p.getLastName() + ", Height: " +
                p.getHeight() + ", Weight: " + p.getWeight() + ", Birthdate: " +
                p.getBirthDate() + ", Nationality: " + p.getNationality().getName() +
                ", TeamId: " + p.getTeamId() + ", Positions: ");
        for(Position pos : p.getPositions())
        {
            System.out.print(pos.getName() + " ");
        }
        System.out.println();
    }
}