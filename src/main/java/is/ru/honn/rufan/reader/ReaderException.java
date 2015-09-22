package is.ru.honn.rufan.reader;

/**
 * ReaderException is thrown when error occurs when
 * reading content from a file and parsing it into object
 */
public class ReaderException extends Exception
{
  public ReaderException()
  {
    super();
  }

  public ReaderException(String message)
  {
    super(message);
  }

  public ReaderException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
