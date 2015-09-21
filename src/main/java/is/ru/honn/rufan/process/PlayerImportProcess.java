package is.ru.honn.rufan.process;

import is.ru.honn.rufan.reader.ReadHandler;
import is.ru.honn.rufan.reader.Reader;
import is.ru.honn.rufan.reader.ReaderFactory;
import is.ruframework.process.RuAbstractProcess;

/**
 * Created by arnib on 20/09/15.
 */
public class PlayerImportProcess extends RuAbstractProcess implements ReadHandler
{
    Reader reader;

    public PlayerImportProcess()
    {
        ReaderFactory factory = ReaderFactory.getReaderFactory();
        reader = factory.getReader("PlayerReader");
        reader.setReadHandler(this);
    }

    public void read(int count, Object object)
    {
        // kallar a reader.Read
    }

    @Override
    public void startProcess()
    {

    }

    public void read()
    {
        //reader.read();
    }
}
