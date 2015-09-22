package is.ru.honn.rufan.reader;

import is.ru.honn.rufan.observers.PlayerObserver;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

/**
 * Factory that gets correct reader and message source from reader.xml
 */
public class ReaderFactory
{
    Logger log = Logger.getLogger(PlayerObserver.class.getName());

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
     * @throws ReaderException
     */
    public Reader getReader(String readerType) throws ReaderException
    {
        Reader reader;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:reader.xml");
        try {
            reader = (Reader) ctx.getBean(readerType);
            return reader;
        } catch(NoSuchBeanDefinitionException e)
        {
            throw new ReaderException(e.getMessage());
        }
    }

    /**
     * Gets message source from reader.xml
     * @param source name of message source requested
     * @return message source
     * @throws ReaderException
     */
    public MessageSource getMessageSource(String source) throws ReaderException
    {
        MessageSource msg;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:app.xml");
        try
        {
            msg = (MessageSource) ctx.getBean(source);
            log.info(msg.toString());
            return msg;
        } catch (NoSuchBeanDefinitionException e)
        {
            throw new ReaderException(e.getMessage());
        }
    }
}
