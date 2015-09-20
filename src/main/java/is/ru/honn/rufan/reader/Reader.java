package is.ru.honn.rufan.reader;

/**
 * Created by arnib on 20/09/15.
 */
public interface Reader
{
    public Object read();
    public Object parse(String content);
    public void setURI(String URI);
    public void setReadHandler(ReadHandler readHandler);
}