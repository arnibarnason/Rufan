package is.ru.honn.rufan.observers;

import java.util.logging.Logger;

/**
 * TeamObserver which logs data from team object
 * argument from Observable class. Not necessary for assignment 2.
 */
public class TeamObserver implements Observer
{
    Logger log = Logger.getLogger(PlayerObserver.class.getName());

    /**
     * Update function called by observable class which
     * is notifying all its observers
     * @param arg Team object
     */
    public void update(Object arg)
    {
        log.info("Team info");
    }
}
