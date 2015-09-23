package is.ru.honn.rufan.observers;

/**
 * Interface for subjects so they can be observed
 * by observer classes
 */
public interface Subject
{
    void addObserver( Observer o );
    void removeObserver( Observer o );
    void notifyObservers(Object object);
}

