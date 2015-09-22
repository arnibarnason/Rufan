package is.ru.honn.rufan.observers;

/**
 * TeamObserver which prints all data from team object
 * argument from Observable class
 */
public class TeamObserver implements Observer
{
    /**
     * Update function called by observable class which
     * is notifying all its observers
     * @param arg Team object
     */
    public void update(Object arg)
    {
        System.out.println("TeamPrinting");
    }
}
