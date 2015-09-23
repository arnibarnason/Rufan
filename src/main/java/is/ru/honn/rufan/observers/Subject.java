package is.ru.honn.rufan.observers;

/**
 * Created by arnib on 23/09/15.
 */
public interface Subject
{
    public void addObserver( Observer o );
    public void removeObserver( Observer o );
    public void notifyObservers(Object object);
}

