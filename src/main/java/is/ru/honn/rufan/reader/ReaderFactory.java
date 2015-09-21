package is.ru.honn.rufan.reader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by arnib on 20/09/15.
 */
public class ReaderFactory
{
    private ReaderFactory factory = new ReaderFactory();
    Reader reader = factory.getReader("playerReader");

    //reader.setReadHandler(this);
    //reader.read();

    public ReaderFactory()
    {
    }

    public static ReaderFactory getReaderFactory()
    {
        // Not correctt
        return new ReaderFactory();
    }

    public Reader getReader(String readerType)
    {
        Reader reader;
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:reader.xml");
        reader = (Reader) ctx.getBean(readerType);
        System.out.println(reader.toString());
        return reader;
    }
}
