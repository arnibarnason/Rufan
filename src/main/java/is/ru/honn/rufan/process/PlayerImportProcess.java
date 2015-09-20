package is.ru.honn.rufan.process;

import is.ru.honn.rufan.reader.ReaderFactory;

/**
 * Created by arnib on 20/09/15.
 */
public class PlayerImportProcess
{
    ReaderFactory reader;
    public PlayerImportProcess()
    {
        reader = new ReaderFactory(
                "http://instagram.com/tags/photooftheday/feed/recent.rss");
    }
    public void read()
    {
        reader.read();
    }
}
