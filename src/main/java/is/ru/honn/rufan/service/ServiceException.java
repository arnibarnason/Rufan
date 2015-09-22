package is.ru.honn.rufan.service;

/**
 * Exception which is thrown when an error occurs
 * within an service
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
