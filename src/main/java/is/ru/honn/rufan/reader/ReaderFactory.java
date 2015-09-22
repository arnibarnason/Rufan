package is.ru.honn.rufan.reader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Factory that gets correct reader and message source from reader.xml
 */
public class ReaderFactory
{
    /**
     * Empty constructor
     */
    public ReaderFactory()
    {
    }

    /**
     * Gets type of reader from reader.xml
     * @param readerType String containing name of reader type
     * @return type of Reader class
     */
    public Reader getReader(String readerType)
    {
        Reader reader;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:reader.xml");
        reader = (Reader) ctx.getBean(readerType);
        System.out.println(reader.toString());
        return reader;
    }

    /**
     * Gets message source from reader.xml
     * @param source name of message source requested
     * @return message source
     */
    public MessageSource getMessageSource(String source)
    {
        MessageSource msg;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:reader.xml");
        msg = (MessageSource) ctx.getBean(source);
        System.out.println(msg.toString());
        return msg;
    }
}
