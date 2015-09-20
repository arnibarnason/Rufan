package is.ru.honn.rufan.process;

import is.ru.honn.rufan.reader.ReadHandler;
import is.ru.honn.rufan.reader.ReaderFactory;
import is.ruframework.process.RuAbstractProcess;

/**
 * Created by arnib on 20/09/15.
 */
public class PlayerImportProcess extends RuAbstractProcess implements ReadHandler
{
    public void read(int count, Object object)
    {

    }

    @Override
    public void startProcess()
    {

    }
    ReaderFactory reader;
    public PlayerImportProcess()
    {
        //reader = new ReaderFactory("http://instagram.com/tags/photooftheday/feed/recent.rss");
    }
    public void read()
    {
       // reader.read();
    }
}
