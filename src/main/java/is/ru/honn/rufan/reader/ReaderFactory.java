package is.ru.honn.rufan.reader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by arnib on 20/09/15.
 */
public class ReaderFactory
{
    public ReaderFactory()
    {
    }

    public Reader getReader(String readerType)
    {
        Reader reader;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:reader.xml");
        reader = (Reader) ctx.getBean(readerType);
        System.out.println(reader.toString());
        return reader;
    }

    public MessageSource getMessageSource(String source)
    {
        MessageSource msg;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:reader.xml");
        msg = (MessageSource) ctx.getBean(source);
        System.out.println(msg.toString());
        return msg;
    }
}
