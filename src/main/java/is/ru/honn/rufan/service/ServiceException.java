package is.ru.honn.rufan.service;

/**
 * Created by arnib on 20/09/15.
 */
public class ServiceException extends Exception
{
    public ServiceException()
    {
    }

    public ServiceException(String message)
    {
        super(message);
    }

    public ServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
