package is.ru.honn.rufan.observers;

/**
 * Interface for subjects so they can be observed
 * by observer classes
 */
public interface Subject
{
    public void addObserver( Observer o );
    public void removeObserver( Observer o );
    public void notifyObservers(Object object);
}

