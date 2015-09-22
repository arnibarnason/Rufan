package is.ru.honn.rufan.observers;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.domain.Position;

/**
 * PlayerObserver which prints all data from player object
 * argument from Observable class (PlayerImportProcess)
 */
public class PlayerObserver implements Observer
{
    /**
     * Update function called by observable class which
     * is notifying all its observers
     * @param arg Player object
     */
    public void update(Object arg)
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