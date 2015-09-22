package is.ru.honn.rufan.observers;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by arnib on 22/09/15.
 */
public class PlayerObserver implements Observer
{
    public void update(Observable o, Object arg)
    {
        System.out.println(arg);
    }
}
