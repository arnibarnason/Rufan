package is.ru.honn.rufan.reader;

/**
 * Separated interface stuff which is essentially
 * unnecessary for this project but is here for
 * educational purposes and playerImportProcess
 * implements it to print objects received from reader
 * and notifies observers
 */
public interface ReadHandler
{
    public void read(int count, Object object);
}
