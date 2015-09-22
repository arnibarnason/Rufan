package is.ru.honn.rufan.reader;

import org.json.simple.JSONObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Abstract reader which contains helper functions
 * and read function for reading uri
 */
public abstract class AbstractReader implements Reader
{
    protected ReadHandler readHandler;
    private String uri;

    public abstract Object parse(String content);

    /**
     * Gets content from uri to read from and parses it
     * @return List of all players/teams parsed
     * @throws ReaderException
     */
    public Object read() throws ReaderException
    {
        ClientRequest CR = new ClientRequest();
        String content = CR.getRequest(uri);
        return this.parse(content);
    }

    /**
     * Set the uri to read from
     * @param URI to read from
     */
    public void setURI(String URI)
    {
        this.uri = URI;
    }

    /**
     * Set the handler
     * @param readHandler seperated interface read handler
     */
    public void setReadHandler(ReadHandler readHandler)
    {
        this.readHandler = readHandler;
    }

    /**
     * Gets integer from name within a json object
     * @param jParent json Object
     * @param name String
     * @return integer
     */
    protected int getInt(JSONObject jParent, String name)
    {
        if(jParent == null)
            return 0;
        Long value = (Long)jParent.get(name);
        if(value == null)
            return 0;
        return value.intValue();
    }

    /**
     * Create new date
     * @param year Year integer
     * @param month Month integer
     * @param date Date integer
     * @return Date object
     */
    protected Date newDate(int year, int month, int date)
    {
        Calendar cal = new GregorianCalendar();
        cal.set(year, month, date);
        return cal.getTime();
    }


    /**
     * Converts date from certain format to a new Date format
     * Format example 2015-08-21T16:19:30.6967613Z
     * @param strDate date in string format
     * @return Date object
     */
    protected Date convertDate(String strDate)
    {
        DateFormat format = new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss", Locale.ENGLISH);
        Date date = null;

        try
        {
            date = format.parse(strDate);
        }
        catch (ParseException e)
        {
            System.out.println("FAIL");
        }
        return date;
    }
}
