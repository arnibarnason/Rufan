package is.ru.honn.rufan.observers;

import is.ru.honn.rufan.domain.Player;
import is.ru.honn.rufan.domain.Position;

import java.util.logging.Logger;

/**
 * PlayerObserver which logs data from player object
 * argument from Observable class (PlayerImportProcess)
 */

public class PlayerObserver implements Observer
{
    private String playerInfo;
    Logger log = Logger.getLogger(PlayerObserver.class.getName());

    /**
     * Update function called by observable class which
     * is notifying all its observers
     * @param arg Player object
     */
    public void update(Object arg)
    {
        Player p = (Player) arg;

        playerInfo = "PlayerID " + p.getPlayerId() + ", Name: " +
                p.getFirstName() + " " + p.getLastName() + ", Height: " +
                p.getHeight() + ", Weight: " + p.getWeight() + ", Birthdate: " +
                p.getBirthDate() + ", Nationality: " + p.getNationality().getName() +
                ", TeamId: " + p.getTeamId() + ", Positions: ";
        for(Position pos : p.getPositions())
        {
            playerInfo += (pos.getName() + " ");
        }
        // Print and log the info
        System.out.println(playerInfo);
        log.info(playerInfo);
    }
}