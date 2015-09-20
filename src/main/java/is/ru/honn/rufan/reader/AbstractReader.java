package is.ru.honn.rufan.reader;

/**
 * Created by arnib on 20/09/15.
 */
public abstract class AbstractReader implements Reader
{
    public abstract Object parse(String content);

    public Object read()
    {
        return null;
    }

    public void setURI(String URI)
    {

    }

    public void setReadHandler(ReadHandler readHandler)
    {

    }
}
