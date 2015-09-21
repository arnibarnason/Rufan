package is.ru.honn.rufan.reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by arnib on 20/09/15.
 */
public abstract class AbstractReader implements Reader
{
    protected ReadHandler readHandler;

    public abstract Object parse(String content);

    public Object read() throws ReaderException
    {
        ClientRequest CR = new ClientRequest();
        String content = CR.getRequest("http://olafurandri.com/honn/players.json");
        return this.parse(content);
//        JSONParser parser = new JSONParser();
//        try {
//            Object obj = parser.parse(new FileReader(URI));
//            JSONObject jsonObject = (JSONObject) obj;
//            return this.parse(jsonObject.toJSONString());
//        } catch (ParseException e) {
//            throw new ReaderException();
//        } catch (FileNotFoundException e) {
//            throw new ReaderException();
//        } catch (IOException e) {
//            throw new ReaderException();
//        }
    }

    public void setURI(String URI)
    {

    }

    public void setReadHandler(ReadHandler readHandler)
    {
        this.readHandler = readHandler;
    }

    protected int getInt(JSONObject jParent, String name)
    {
        if(jParent == null)
            return 0;
        Long value = (Long)jParent.get(name);
        if(value == null)
            return 0;
        return value.intValue();
    }

    protected Date newDate(int year, int month, int date)
    {
        Calendar cal = new GregorianCalendar();
        cal.set(year, month, date);
        return cal.getTime();
    }

    // Format example 2015-08-21T16:19:30.6967613Z
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
