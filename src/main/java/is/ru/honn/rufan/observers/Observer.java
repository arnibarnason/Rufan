package is.ru.honn.rufan.observers;

/**
 * Observer interface for those who want
 * to be able to be notified of changes from
 * observable classes
 */
public interface Observer
{
    public void update(Object object);
}

