package is.ru.honn.rufan.reader;

/**
 * Interface for creating a reader
 */
public interface Reader
{
    public Object read() throws ReaderException;
    public Object parse(String content);
    public void setURI(String URI);
    public void setReadHandler(ReadHandler readHandler);
}